# Example of use

## Sample GET request

```
$ curl -v -i http://127.0.0.1:9091/api/operations
*   Trying 127.0.0.1...
* TCP_NODELAY set
* Connected to 127.0.0.1 (127.0.0.1) port 9091 (#0)
> GET /api/operations HTTP/1.1
> Host: 127.0.0.1:9091
> User-Agent: curl/7.64.0
> Accept: */*
> 
< HTTP/1.1 200 
HTTP/1.1 200 
< Content-Type: application/hal+json;charset=UTF-8
Content-Type: application/hal+json;charset=UTF-8
< Transfer-Encoding: chunked
Transfer-Encoding: chunked
< Date: Thu, 27 Jun 2019 15:18:12 GMT
Date: Thu, 27 Jun 2019 15:18:12 GMT

< 
{
  "_links" : {
    "self" : {
      "href" : "http://127.0.0.1:9091/api/operations"
    }
  }
* Connection #0 to host 127.0.0.1 left intact
}
```

## To create an item

```
$ curl -v -i -H "Content-Type:application/json" -d "{\"name\":\"Diet Problem\"}" http://127.0.0.1:9091/api/operations
*   Trying 127.0.0.1...
* TCP_NODELAY set
* Connected to 127.0.0.1 (127.0.0.1) port 9091 (#0)
> POST /api/operations HTTP/1.1
> Host: 127.0.0.1:9091
> User-Agent: curl/7.64.0
> Accept: */*
> Content-Type:application/json
> Content-Length: 23
> 
* upload completely sent off: 23 out of 23 bytes
< HTTP/1.1 201 
HTTP/1.1 201 
< Location: http://127.0.0.1:9091/api/operations/1
Location: http://127.0.0.1:9091/api/operations/1
< Content-Type: application/hal+json;charset=UTF-8
Content-Type: application/hal+json;charset=UTF-8
< Transfer-Encoding: chunked
Transfer-Encoding: chunked
< Date: Thu, 27 Jun 2019 15:20:10 GMT
Date: Thu, 27 Jun 2019 15:20:10 GMT

< 
{
  "name" : "Diet Problem",
  "_links" : {
    "self" : {
      "href" : "http://127.0.0.1:9091/api/operations/1"
    },
    "operations" : {
      "href" : "http://127.0.0.1:9091/api/operations/1"
    }
  }
* Connection #0 to host 127.0.0.1 left intact
}
```
