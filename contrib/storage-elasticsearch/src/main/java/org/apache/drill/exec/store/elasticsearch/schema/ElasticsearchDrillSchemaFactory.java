/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.drill.exec.store.elasticsearch.schema;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.calcite.adapter.elasticsearch.ElasticsearchSchemaFactory;
import org.apache.calcite.schema.SchemaPlus;
import org.apache.drill.exec.store.AbstractSchemaFactory;
import org.apache.drill.exec.store.SchemaConfig;
import org.apache.drill.exec.store.elasticsearch.ElasticsearchStoragePlugin;

public class ElasticsearchDrillSchemaFactory extends AbstractSchemaFactory {

  private final ElasticsearchStoragePlugin plugin;
  private final ElasticsearchSchemaFactory delegate;

  public ElasticsearchDrillSchemaFactory(String name, ElasticsearchStoragePlugin plugin) {
    super(name);
    this.plugin = plugin;
    this.delegate = new ElasticsearchSchemaFactory();
  }

  @Override
  public void registerSchemas(SchemaConfig schemaConfig, SchemaPlus parent) throws JsonProcessingException {
    ElasticsearchDrillSchema schema = new ElasticsearchDrillSchema(getName(), plugin,
        delegate.create(parent, getName(), plugin.getConfig().toConfigMap()));
    parent.add(getName(), schema);
  }
}
