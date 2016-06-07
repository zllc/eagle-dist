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

package org.apache.eagle.datastream.core

import com.typesafe.config.Config
import org.apache.eagle.datastream.utils.NodeNameSelector
import org.jgrapht.experimental.dag.DirectedAcyclicGraph
import org.slf4j.LoggerFactory

/**
 * to set name for each StreamProducer
 * 1. if name is given programatically, then use this name
 * 2. otherwise use name generated by scala internally
 */
case class StreamNameExpansion(config: Config) extends StreamDAGExpansion(config){
  val LOG = LoggerFactory.getLogger(classOf[StreamNameExpansion])

  override def expand(dag: DirectedAcyclicGraph[StreamProducer[Any], StreamConnector[Any,Any]]) = {
    val iter = dag.iterator()
    while(iter.hasNext){
      val sp = iter.next()
      sp.name = NodeNameSelector(sp).getName
    }
  }
}


object StreamNameExpansion{
  def apply()(implicit config:Config, dag: DirectedAcyclicGraph[StreamProducer[Any], StreamConnector[Any,Any]]): StreamNameExpansion ={
    val e = StreamNameExpansion(config)
    e.expand(dag)
    e
  }
}