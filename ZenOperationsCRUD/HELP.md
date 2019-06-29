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

## Again, create an item

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
< Date: Sat, 29 Jun 2019 10:21:45 GMT
Date: Sat, 29 Jun 2019 10:21:45 GMT

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

## Retrieve this item by id

```
$ curl -v -i http://127.0.0.1:9091/api/operations/1
*   Trying 127.0.0.1...
* TCP_NODELAY set
* Connected to 127.0.0.1 (127.0.0.1) port 9091 (#0)
> GET /api/operations/1 HTTP/1.1
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
< Date: Sat, 29 Jun 2019 10:23:19 GMT
Date: Sat, 29 Jun 2019 10:23:19 GMT

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

## Update by id

```
$ curl -v -i -X PUT -H "Content-Type:application/json" -d "{\"name\":\"Schedule Problem\"}" http://127.0.0.1:9091/api/operations/1
*   Trying 127.0.0.1...
* TCP_NODELAY set
* Connected to 127.0.0.1 (127.0.0.1) port 9091 (#0)
> PUT /api/operations/1 HTTP/1.1
> Host: 127.0.0.1:9091
> User-Agent: curl/7.64.0
> Accept: */*
> Content-Type:application/json
> Content-Length: 27
> 
* upload completely sent off: 27 out of 27 bytes
< HTTP/1.1 201 
HTTP/1.1 201 
< Location: http://127.0.0.1:9091/api/operations/1
Location: http://127.0.0.1:9091/api/operations/1
< Content-Type: application/hal+json;charset=UTF-8
Content-Type: application/hal+json;charset=UTF-8
< Transfer-Encoding: chunked
Transfer-Encoding: chunked
< Date: Sat, 29 Jun 2019 10:25:18 GMT
Date: Sat, 29 Jun 2019 10:25:18 GMT

< 
{
  "name" : "Schedule Problem",
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

## Delete by id

```
$ curl -v -i -X DELETE http://127.0.0.1:9091/api/operations/1
*   Trying 127.0.0.1...
* TCP_NODELAY set
* Connected to 127.0.0.1 (127.0.0.1) port 9091 (#0)
> DELETE /api/operations/1 HTTP/1.1
> Host: 127.0.0.1:9091
> User-Agent: curl/7.64.0
> Accept: */*
> 
< HTTP/1.1 204 
HTTP/1.1 204 
< Date: Sat, 29 Jun 2019 10:29:14 GMT
Date: Sat, 29 Jun 2019 10:29:14 GMT

< 
* Connection #0 to host 127.0.0.1 left intact

```

## And verify

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
< Date: Sat, 29 Jun 2019 10:29:25 GMT
Date: Sat, 29 Jun 2019 10:29:25 GMT

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

