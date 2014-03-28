Thrift Transport for Elasticsearch
==================================

The thrift transport plugin allows to use the REST interface over [thrift](http://thrift.apache.org/) on top of HTTP.

In order to install the plugin, simply run: `bin/plugin -install elasticsearch/elasticsearch-transport-thrift/1.8.0`.

* For master elasticsearch versions, look at [master branch](https://github.com/elasticsearch/elasticsearch-transport-thrift/tree/master).
* For 1.1.x elasticsearch versions, look at [es-1.1 branch](https://github.com/elasticsearch/elasticsearch-transport-thrift/tree/es-1.1).
* For 1.0.x elasticsearch versions, look at [es-1.0 branch](https://github.com/elasticsearch/elasticsearch-transport-thrift/tree/es-1.0).
* For 0.90.x elasticsearch versions, look at [es-0.90 branch](https://github.com/elasticsearch/elasticsearch-transport-thrift/tree/es-0.90).

|   Thrift Transport Plugin   | elasticsearch         | Thrift | Release date |
|-----------------------------|-----------------------|--------|:------------:|
| 1.9.0-SNAPSHOT              | 0.90.10 -> 0.90       | 0.9.1  |  XXXX-XX-XX  |
| 1.8.0                       | 0.90.10 -> 0.90       | 0.9.1  |  2014-02-11  |
| 1.7.0                       | 0.90.10 -> 0.90       | 0.9.1  |  2014-01-13  |
| 1.6.0                       | 0.90.3 -> 0.90.9      | 0.9.1  |  2013-09-02  |
| 1.5.0                       | 0.90.0 -> 0.90.2      | 0.9.0  |  2013-02-26  |
| 1.4.0                       | 0.19.9 -> 0.20        | 0.9.0  |  2012-10-16  |
| 1.3.0                       | 0.19.9 -> 0.20        | 0.8.0  |  2012-08-23  |
| 1.2.0                       | 0.19.0 -> 0.19.8      | 0.8.0  |  2012-04-24  |
| 1.1.0                       | 0.19.0 -> 0.19.8      | 0.6.1  |  2012-02-07  |
| 1.0.0                       | 0.18                  | 0.6.1  |  2011-12-05  |

Please read documentation relative to the version you are using:

* [1.9.0-SNAPSHOT](https://github.com/elasticsearch/elasticsearch-transport-thrift/blob/es-0.90/README.md)
* [1.8.0](https://github.com/elasticsearch/elasticsearch-transport-thrift/blob/v1.8.0/README.md)
* [1.7.0](https://github.com/elasticsearch/elasticsearch-transport-thrift/blob/v1.7.0/README.md)
* [1.6.0](https://github.com/elasticsearch/elasticsearch-transport-thrift/blob/v1.6.0/README.md)
* [1.5.0](https://github.com/elasticsearch/elasticsearch-transport-thrift/blob/v1.5.0/README.md)
* [1.4.0](https://github.com/elasticsearch/elasticsearch-transport-thrift/blob/v1.4.0/README.md)
* [1.3.0](https://github.com/elasticsearch/elasticsearch-transport-thrift/blob/v1.3.0/README.md)
* [1.2.0](https://github.com/elasticsearch/elasticsearch-transport-thrift/blob/v1.2.0/README.md)
* [1.1.0](https://github.com/elasticsearch/elasticsearch-transport-thrift/blob/v1.1.0/README.md)
* [1.0.0](https://github.com/elasticsearch/elasticsearch-transport-thrift/blob/v1.0.0/README.md)

The thrift definition can be found under the `elasticsearch.thrift` file.

The thrift [schema](https://github.com/elasticsearch/elasticsearch-transport-thrift/blob/master/elasticsearch.thrift) can be used to generate thrift clients.

* `thrift.port`: The port to bind to. Defaults to `9500-9600`.
* `thrift.frame`: Defaults to `-1`, which means no framing. Set to a higher value to specify the frame size (like `15mb`).

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
