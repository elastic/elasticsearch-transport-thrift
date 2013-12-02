Thrift Transport for ElasticSearch
==================================

The thrift transport plugin allows to use the REST interface over [thrift](http://thrift.apache.org/) on top of HTTP.

In order to install the plugin, simply run: `bin/plugin -install elasticsearch/elasticsearch-transport-thrift/1.6.0`.

<table>
	<thead>
		<tr>
			<td>Thrift Plugin</td>
			<td>ElasticSearch</td>
			<td>Thrift</td>
			<td>Release date</td>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>1.7.0-SNASPHOT (master)</td>
			<td>0.90.3 -> master</td>
			<td>0.9.1</td>
			<td></td>
		</tr>
        <tr>
			<td>1.6.0</td>
			<td>0.90.3 -> master</td>
			<td>0.9.1</td>
			<td>02/09/2013</td>
		</tr>
		<tr>
			<td>1.5.0</td>
			<td>0.90 -> 0.90.2</td>
			<td>0.9.0</td>
			<td>26/02/2013</td>
		</tr>
		<tr>
			<td>1.4.0</td>
			<td>0.19.9 -> 0.20</td>
			<td>0.9.0</td>
			<td>16/10/2012</td>
		</tr>
		<tr>
			<td>1.3.0</td>
			<td>0.19.9 -> 0.20</td>
			<td>0.8.0</td>
			<td>23/08/2012</td>
		</tr>
		<tr>
			<td>1.2.0</td>
			<td>0.19.0 -> 0.19.8</td>
			<td>0.8.0</td>
			<td>24/04/2012</td>
		</tr>
		<tr>
			<td>1.1.0</td>
			<td>0.19.0 -> 0.19.8</td>
			<td>0.6.1</td>
			<td>07/02/2012</td>
		</tr>
        <tr>
			<td>1.0.0</td>
			<td>0.18</td>
			<td>0.6.1</td>
			<td>05/12/2011</td>
		</tr>
	</tbody>
</table>

The thrift definition can be found under the `elasticsearch.thrift` file.

The thrift [schema](https://github.com/elasticsearch/elasticsearch-transport-thrift/blob/master/elasticsearch.thrift) can be used to generate thrift clients.

* `thrift.port`: The port to bind to. Defaults to `9500-9600`.
* `thrift.frame`: Defaults to `-1`, which means no framing. Set to a higher value to specify the frame size (like `15mb`).

License
-------

    This software is licensed under the Apache 2 license, quoted below.

    Copyright 2009-2013 Shay Banon and ElasticSearch <http://www.elasticsearch.org>

    Licensed under the Apache License, Version 2.0 (the "License"); you may not
    use this file except in compliance with the License. You may obtain a copy of
    the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
    WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
    License for the specific language governing permissions and limitations under
    the License.
