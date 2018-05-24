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

package org.elasticsearch.action.admin.indices.unfreeze;

import org.elasticsearch.common.xcontent.XContentParser;
import org.elasticsearch.test.AbstractStreamableXContentTestCase;

public class UnfreezeIndexResponseTests extends AbstractStreamableXContentTestCase<UnfreezeIndexResponse> {

    @Override
    protected UnfreezeIndexResponse doParseInstance(XContentParser parser) {
        return UnfreezeIndexResponse.fromXContent(parser);
    }

    @Override
    protected UnfreezeIndexResponse createTestInstance() {
        boolean acknowledged = randomBoolean();
        boolean shardsAcknowledged = acknowledged && randomBoolean();
        return new UnfreezeIndexResponse(acknowledged, shardsAcknowledged);
    }

    @Override
    protected UnfreezeIndexResponse createBlankInstance() {
        return new UnfreezeIndexResponse();
    }

    @Override
    protected UnfreezeIndexResponse mutateInstance(UnfreezeIndexResponse response) {
        if (randomBoolean()) {
            boolean acknowledged = response.isAcknowledged() == false;
            boolean shardsAcknowledged = acknowledged && response.isShardsAcknowledged();
            return new UnfreezeIndexResponse(acknowledged, shardsAcknowledged);
        } else {
            boolean shardsAcknowledged = response.isShardsAcknowledged() == false;
            boolean acknowledged = shardsAcknowledged || response.isAcknowledged();
            return new UnfreezeIndexResponse(acknowledged, shardsAcknowledged);
        }
    }
}