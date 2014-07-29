Thrift Transport for Elasticsearch
==================================

The thrift transport plugin allows to use the REST interface over [thrift](http://thrift.apache.org/) on top of HTTP.

In order to install the plugin, simply run: `bin/plugin -install elasticsearch/elasticsearch-transport-thrift/2.0.0`.

* For master elasticsearch versions, look at [master branch](https://github.com/elasticsearch/elasticsearch-transport-thrift/tree/master).
* For 1.x.x elasticsearch versions, look at [es-1.x branch](https://github.com/elasticsearch/elasticsearch-transport-thrift/tree/es-1.x).
* For 1.3.x elasticsearch versions, look at [es-1.3 branch](https://github.com/elasticsearch/elasticsearch-transport-thrift/tree/es-1.3).
* For 1.2.x elasticsearch versions, look at [es-1.2 branch](https://github.com/elasticsearch/elasticsearch-transport-thrift/tree/es-1.2).
* For 1.1.x elasticsearch versions, look at [es-1.1 branch](https://github.com/elasticsearch/elasticsearch-transport-thrift/tree/es-1.1).
* For 1.0.x elasticsearch versions, look at [es-1.0 branch](https://github.com/elasticsearch/elasticsearch-transport-thrift/tree/es-1.0).
* For 0.90.x elasticsearch versions, look at [es-0.90 branch](https://github.com/elasticsearch/elasticsearch-transport-thrift/tree/es-0.90).

|   Thrift Transport Plugin   | elasticsearch         | Thrift | Release date |
|-----------------------------|-----------------------|--------|:------------:|
| 2.4.0-SNAPSHOT              | 1.x.0 -> 1.x          | 0.9.1  |  XXXX-XX-XX  |

Please read documentation relative to the version you are using:

* [2.4.0-SNAPSHOT](https://github.com/elasticsearch/elasticsearch-transport-thrift/blob/es-1.x/README.md)

The thrift definition can be found under the `elasticsearch.thrift` file.

The thrift [schema](https://github.com/elasticsearch/elasticsearch-transport-thrift/blob/master/elasticsearch.thrift) can be used to generate thrift clients.

* `thrift.port`: The port to bind to. Defaults to `9500-9600`.
* `thrift.frame`: Defaults to `-1`, which means no framing. Set to a higher value to specify the frame size (like `15mb`).
* `thrift.bind_host`: Set explicit bindings for thrift protocol. Defaults to `transport.bind_host` or `transport.host`.
* `thrift.publish_host`: Set explicit bindings for thrift protocol. Defaults to `transport.publish_host` or `transport.host`.
* `thrift.protocol`: `binary` (default) which use Binary protocol or `compact` which uses Compact Protocol. See [Thrift documentation](https://thrift.apache.org/docs/concepts).

License
-------

    This software is licensed under the Apache 2 license, quoted below.

    Copyright 2009-2014 Elasticsearch <http://www.elasticsearch.org>

    Licensed under the Apache License, Version 2.0 (the "License"); you may not
    use this file except in compliance with the License. You may obtain a copy of
    the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
    WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
    License for the specific language governing permissions and limitations under
    the License.
