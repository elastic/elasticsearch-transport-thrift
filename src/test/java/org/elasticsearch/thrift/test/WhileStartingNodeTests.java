/*
 * Licensed to Elasticsearch under one or more contributor
 * license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright
 * ownership. Elasticsearch licenses this file to you under
 * the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.elasticsearch.thrift.test;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.elasticsearch.common.base.Predicate;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.util.concurrent.EsExecutors;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;
import org.elasticsearch.plugins.PluginsService;
import org.elasticsearch.test.ElasticsearchTestCase;
import org.elasticsearch.thrift.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.elasticsearch.test.ElasticsearchTestCase.awaitBusy;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class WhileStartingNodeTests extends ElasticsearchTestCase {

    private TTransport transport;
    private Rest.Client client;
    private Thread nodeThread;
    private boolean stopped = false;

    @Before
    public void beforeTest() throws IOException, TTransportException {
        // Starting a node in a Thread
        nodeThread = EsExecutors.daemonThreadFactory("node").newThread(new Runnable() {
            @Override
            public void run() {
                Node node = NodeBuilder.nodeBuilder().settings(ImmutableSettings.builder()
                        .put("thrift.port", SimpleThriftTests.getPort(0))
                        .put("path.home", createTempDir())
                        .put("plugins." + PluginsService.LOAD_PLUGIN_FROM_CLASSPATH, true)
                        .build()
                ).node();

                while (!stopped) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        if (node != null) {
                            node.close();
                        }
                    }
                }

                node.close();
            }
        });

        nodeThread.start();

        transport = new TSocket("localhost", SimpleThriftTests.getPort(0));
        TProtocol protocol = new TBinaryProtocol(transport);
        client = new Rest.Client(protocol);
    }

    @After
    public void afterTest() {
        if (transport != null) transport.close();
        if (nodeThread != null) stopped = true;

        // We wait until nodeThread is stopped
        try {
            assertThat(awaitBusy(new Predicate<Object>() {
                public boolean apply(Object obj) {
                    return !nodeThread.isAlive();
                }
            }, 5, TimeUnit.SECONDS), equalTo(true));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test case for #16: 2.0.0 RC1 fails with NullPointerException[null] when accessed during startup
     * https://github.com/elasticsearch/elasticsearch-transport-thrift/issues/16
     * @throws Exception
     */
    @Test
    public void testWhileNodeIsNotStarted() throws Exception {
        assertThat(awaitBusy(new Predicate<Object>() {
            public boolean apply(Object obj) {
                try {
                    transport.open();
                    return true;
                } catch (TTransportException e) {
                    return false;
                }
            }
        }, 15, TimeUnit.SECONDS), equalTo(true));

        assertThat(awaitBusy(new Predicate<Object>() {
            public boolean apply(Object obj) {
                try {
                    RestRequest request = new RestRequest(Method.GET, "/_cluster/health");
                    RestResponse response = client.execute(request);
                    assertThat(response.status, notNullValue());
                    if (!response.status.equals(Status.OK)) {
                        return false;
                    }

                    // When we have a OK, that's fine
                    return true;
                } catch (TException e) {
                    return false;
                }
            }
        }, 5, TimeUnit.SECONDS), equalTo(true));
    }
}
