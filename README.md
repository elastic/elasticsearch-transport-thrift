Thrift Transport for Elasticsearch
==================================

The thrift transport plugin allows to use the REST interface over [thrift](http://thrift.apache.org/) on top of HTTP.

In order to install the plugin, simply run: `bin/plugin -install elasticsearch/elasticsearch-transport-thrift/2.0.0`.

* For 1.0.x elasticsearch versions, look at [master branch](https://github.com/elasticsearch/elasticsearch-transport-thrift/tree/master).
* For 0.90.x elasticsearch versions, look at [1.x branch](https://github.com/elasticsearch/elasticsearch-transport-thrift/tree/1.x).


|   Thrift Transport Plugin   | elasticsearch         | Thrift | Release date |
|-----------------------------|-----------------------|--------|:------------:|
| 2.0.0                       | 1.0.0.RC1 -> master   | 0.9.1  |  2014-03-04  |
| 2.0.0.RC2                   | 1.0.0.RC1 -> master   | 0.9.1  |  2014-02-11  |
| 2.0.0.RC1                   | 1.0.0.RC1 -> master   | 0.9.1  |  2014-01-15  |

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
