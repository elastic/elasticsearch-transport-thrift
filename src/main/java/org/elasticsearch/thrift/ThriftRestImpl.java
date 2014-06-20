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

package org.elasticsearch.thrift;

import org.apache.thrift.TException;
import org.elasticsearch.common.component.AbstractComponent;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.rest.RestController;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

/**
 */
public class ThriftRestImpl extends AbstractComponent implements Rest.Iface {

    private final RestController restController;

    @Inject
    public ThriftRestImpl(Settings settings, RestController restController) {
        super(settings);
        this.restController = restController;
    }

    @Override
    public org.elasticsearch.thrift.RestResponse execute(RestRequest request) throws TException {
        if (logger.isTraceEnabled()) {
            logger.trace("thrift message {}", request);
        }
        final CountDownLatch latch = new CountDownLatch(1);
        final AtomicReference<org.elasticsearch.thrift.RestResponse> ref = new AtomicReference<org.elasticsearch.thrift.RestResponse>();
        
        ThriftRestRequest thriftRestRequest = new ThriftRestRequest(request);
        ThriftRestChannel thriftRestChannel = new ThriftRestChannel(thriftRestRequest, latch, ref);;
        
        restController.dispatchRequest(thriftRestRequest, thriftRestChannel);
        
        try {
            latch.await();
            return ref.get();
        } catch (Exception e) {
            throw new TException("failed to generate response", e);
        }
    }
}
