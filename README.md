Thrift Transport for ElasticSearch
==================================

The thrift transport plugin allows to use the REST interface over [thrift|http://thrift.apache.org/] on top of HTTP.

In order to install the plugin, simply run: `bin/plugin -install elasticsearch/elasticsearch-transport-thrift/1.0.0`.

    ------------------------------------
    | Thrift Plugin | Elasticsearch    |
    ------------------------------------
    | master        | 0.18 -> master   |
    ------------------------------------
    | 1.0.0         | 0.18 -> master   |
    ------------------------------------

The plugin works with thrift version `0.6.1`, the definition can be found under the `elasticsearch.thrift` file.
