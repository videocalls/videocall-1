#!/usr/bin/python
import sys 

from urllib2 import HTTPPasswordMgrWithDefaultRealm, HTTPBasicAuthHandler, Request, build_opener
from urllib import urlencode

port = ("8096", "8091", "8090")
def call_url(port):
	handler_chain = []
	url = "http://127.0.0.1:"+port+"/cmm/cronSvcTermsEmail.do"
	director = build_opener(*handler_chain)
	req = Request(url) # headers = {'Accept' : 'application/json'})
	try:
		result = director.open(req)
		return result.read()
	except:
		return '-1'

index = 0
result = call_url(port[index])
while result == '-1' and index < (len(port) - 1):
	print 'try'
	index = index + 1
	result = call_url(port[index])
print result
