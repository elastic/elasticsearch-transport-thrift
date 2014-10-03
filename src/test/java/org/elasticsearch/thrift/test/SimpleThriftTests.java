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

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.plugins.PluginsService;
import org.elasticsearch.test.ElasticsearchIntegrationTest;
import org.elasticsearch.thrift.Method;
import org.elasticsearch.thrift.RestRequest;
import org.elasticsearch.thrift.RestResponse;
import org.elasticsearch.thrift.Status;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Map;

import static org.hamcrest.Matchers.*;

/**
 * @author kimchy (shay.banon)
 */

@ElasticsearchIntegrationTest.ClusterScope(transportClientRatio = 0.0, scope = ElasticsearchIntegrationTest.Scope.SUITE)
public class SimpleThriftTests extends ElasticsearchIntegrationTest {

    private TTransport transport;
    private org.elasticsearch.thrift.Rest.Client client;

    public static int getPort(int nodeOrdinal) {
        try {
            return PropertiesHelper.getAsInt("plugin.port")
                    + nodeOrdinal * 10;
        } catch (IOException e) {
        }

        return -1;
    }

    @Before
    public void beforeTest() throws IOException, TTransportException {
        int port = getPort(randomInt(cluster().size()-1));
        logger.info("  --> Testing Thrift on port [{}]", port);
        transport = new TSocket("localhost", port);
        TProtocol protocol = new TBinaryProtocol(transport);
        client = new org.elasticsearch.thrift.Rest.Client(protocol);
        transport.open();
    }

    @Override
    protected Settings nodeSettings(int nodeOrdinal) {
        return ImmutableSettings.builder()
                .put("thrift.port", getPort(nodeOrdinal))
                .put("plugins." + PluginsService.LOAD_PLUGIN_FROM_CLASSPATH, true)
                .build();
    }

    @After
    public void afterTest() {
        if (transport != null) transport.close();
    }

    @Test
    public void testSimpleApis() throws Exception {
        RestRequest request = new RestRequest(Method.POST, "/test/type1/1");
        request.setBody(ByteBuffer.wrap(XContentFactory.jsonBuilder().startObject()
                .field("field", "value")
                .endObject().bytes().copyBytesArray().array()));
        RestResponse response = client.execute(request);
        Map<String, Object> map = parseBody(response);
        assertThat(response.getStatus(), equalTo(Status.CREATED));
        assertThat(map.get("_index").toString(), equalTo("test"));
        assertThat(map.get("_type").toString(), equalTo("type1"));
        assertThat(map.get("_id").toString(), equalTo("1"));

        request = new RestRequest(Method.GET, "/test/type1/1");
        response = client.execute(request);
        map = parseBody(response);
        assertThat(response.getStatus(), equalTo(Status.OK));
        assertThat(map.get("_index").toString(), equalTo("test"));
        assertThat(map.get("_type").toString(), equalTo("type1"));
        assertThat(map.get("_id").toString(), equalTo("1"));
        assertThat(map.get("_source"), notNullValue());
        assertThat(map.get("_source"), instanceOf(Map.class));
        assertThat(((Map<String, String>)map.get("_source")).get("field"), is("value"));

        request = new RestRequest(Method.GET, "/_cluster/health");
        response = client.execute(request);
        assertThat(response.getStatus(), equalTo(Status.OK));

        request = new RestRequest(Method.GET, "/bogusindex");
        response = client.execute(request);
        assertThat(response.getStatus(), equalTo(Status.NOT_FOUND));
    }

    private Map<String, Object> parseBody(RestResponse response) throws IOException {
        return XContentFactory.xContent(XContentType.JSON).createParser(response.bufferForBody().array(), response.bufferForBody().arrayOffset(), response.bufferForBody().remaining()).map();
    }
}
