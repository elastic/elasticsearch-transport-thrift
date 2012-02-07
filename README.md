Thrift Transport for ElasticSearch
==================================

The thrift transport plugin allows to use the REST interface over [thrift|http://thrift.apache.org/] on top of HTTP.

In order to install the plugin, simply run: `bin/plugin -install elasticsearch/elasticsearch-transport-thrift/1.1.0`.

    ------------------------------------
    | Thrift Plugin | Elasticsearch    |
    ------------------------------------
    | master        | 0.19 -> master   |
    ------------------------------------
    | 1.1.0         | 0.19 -> master   |
    ------------------------------------
    | 1.0.0         | 0.18             |
    ------------------------------------

The plugin works with thrift version `0.6.1`, the definition can be found under the `elasticsearch.thrift` file.

The thrift [schema|https://github.com/elasticsearch/elasticsearch-transport-thrift/blob/master/elasticsearch.thrift] can be used to generate thrift clients.

* `thrift.port`: The port to bind to. Defaults to `9500-9600`.
* `thrift.frame`: Defaults to `-1`, which means no framing. Set to a higher value to specify the frame size (like `15mb`).
