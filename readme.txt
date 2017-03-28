*** Readme *****
Createb by Ducvu
Contact: duc0103195@gmail.com

****************

1. Hướng dẫn chạy elasticsearch
	- Elasticsearch đã đánh chỉ mục dữ liệu trong index yahoo, tất cả được đặt trong document : news
	- Để chạy elasticsearch từ thư mục elas ngoài cùng, di chuyển vào elasticsearch-5.2.2/bin
	- Chạy ./elasticsearch
	- elasticsearch là một reshful nên nó sẽ tự động chạy và chạy ở: localhost:9200,
	có thể lên trình duyệt truy cập vào localhost:9200 để kiểm tra
2. Hướng dẫn dùng các hàm trong api
	- Project webapp_inf_retrival được tạo trên netbean, chỉ có 2 file chính cần chú ý là:
	FormatData.java và ParseJson.java trong package elas.parse_json
	- elasticsearch, tìm kiểm và trả về rất nhiều kết quả, vì vậy trong api sẽ xây dựng mặc định mỗi
	lần lấy về sẽ lấy một trang gồm 5 bộ dữ liệu
	- Sử dụng luôn phương thức defaultSearch() trong Lớp ParseJson.java, cần truyền vào 2 đầu vào là
	câu query tìm kiếm và số thứ tự trang, 1,2 hoặc 3,. . . 
	- Phương thức này sẽ trả về một json là body cho tìm kiếm,
	- Dùng phương thức POST gửi yêu cầu đến resful : http://localhost:9200/yahoo/news/_search?search_type=dfs_query_then_fetch
	với body là string nhận được từ phương thức trên và resful sẽ trả về kết quả là một json


3 Hướng dẫn lấy kết quả tìm kiếm:
	- Kết quả sau khi tìm kiếm trả về là một json có dạng như sau:
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
	- kết quả trả về nằm trong thuộc tính hits.hits[], gồm một mảng các kết quả trả về, mỗi phần tử cảu mảng có các thuộc tính, id, type,
	giá trị của các document nằm trong thuộc tính _source

