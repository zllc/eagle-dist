/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.eagle.security.userprofile.impl;

import org.apache.eagle.ml.model.MLModelAPIEntity;
import org.apache.eagle.security.userprofile.UserProfileMLAlgorithmEvaluator;
import org.apache.eagle.security.userprofile.model.UserProfileKDEModel;
import org.apache.eagle.security.userprofile.model.UserProfileKDEModelEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public abstract class AbstractUserProfileKDEEvaluator extends UserProfileMLAlgorithmEvaluator<UserProfileKDEModel> {
    private final static Logger LOG = LoggerFactory.getLogger(AbstractUserProfileKDEEvaluator.class);

    @Override
    public UserProfileKDEModel convert(MLModelAPIEntity entity) throws IOException {
        try {
            return UserProfileKDEModelEntity.deserializeModel(entity);
        } catch (IOException e) {
            LOG.error(e.getMessage(),e);
            throw e;
        }
    }
}