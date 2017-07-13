# : Project of Information and Retrival at Ha Noi University of science and technology
## Introduction
****************
In this project, I'm using Elasticsearch 2.4 to create and configure a Index with one-node in local computer for searching news crawled from yahoo. In this, I'm only configure and push data to Elasticsearch. But I leart many things and concept in Elasticsearch such as: Cluster, Index, Analyzers for analyze information when indexing and searching, and somes skill in searching include: rank page algorithm, vector-space model and many knowledge in Information Retrival.

## Installation and Running
1. Guide to run Elasticsearch
	- Elasticsearch was indexed with information crawled from Yahoo, in index "yahoo", all was stored at document "news"
	- To run Elasticsearch from the folder, cd to elasticsearch-2.4.0/bin
	- running ./elasticsearch
	- elasticsearch ís Restful apt so any one would like to search or analyze can connect to: localhost:9200
	- in this project I don't code or using apt to search I'm responsible to indexing and configure Elasticsearch
2. Guide to use some api is createb by me for using in future
	- Project webapp_inf_retrival just contains two file FormatData.java and ParseJson.java in package elas.parse_json
	- Elasticsearch, when searching results is very many, so in apt is built for retrive 5 documents per time
	- Can directly use the method defaultSearch() in ParseJson.java class, but you must give input parameter the number of page, 1,2 or 3,. . . 
	- This method just only return a body json for searching
	- After that user using this method must implement a POST request sent to resful api at : http://localhost:9200/yahoo/news/_search?search_type=dfs_query_then_fetch with body is json from above method
3 Get the results of searching:
	- results be returned has followed format:
	{
  "took": 171,
  "timed_out": false,
  "_shards": {
    "total": 5,
    "successful": 5,
    "failed": 0
  },
  "hits": {
    "total": 4472,
    "max_score": 28.3012,
    "hits": [
      {
        "_index": "yahoo",
        "_type": "news",
        "_id": "1865",
        "_score": 12.90229,
        "_source": {
          "content": "Apple CEO Tim Cook broke down",
          "description": "Apple CEO Tim",
          "title": "Apple CEO uses sofa",
          "url": "https://www.yahoo.com/tech/apple-ceo-tim-cook-qualcomm-lawsuit-002605447.html"
        }
      },
      {
        "_index": "yahoo",
        "_type": "news",
        "_id": "5326",
        "_score": 12.488981,
        "_source": {
          "content": "First things first",
          "description": "First things",
          "title": "There Are Many Ways You Can ",
          "url": "https://www.yahoo.com/tech/there-are-many-ways-you-can-secure-your-android-lock-79974274088.html"
        }
      }
    ]
  }
}
	- results in hits.hits[] property
## Authority
Minh-Duc Vu from Ha Noi university of science and technology

Contact: duc0103195@gmail.com
