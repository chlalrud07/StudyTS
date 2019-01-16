from django.shortcuts import render
from django.shortcuts import render
from django.http import JsonResponse, HttpResponse
from django.views.decorators.csrf import csrf_exempt
from datetime import datetime

import json
import mysql.connector
import hashlib


@csrf_exempt
def upload(request):
    myDB = mysql.connector.connect(
        host='localhost',
        user='StudyTS',
        passwd='qwer1234',
        database='StudyTS',
        charset='utf8'
    )
    cursor = myDB.cursor(dictionary=True)
    sql = 'INSERT INTO post(post_id, created_at, author_id, title, content) values (%s, %s, %s, %s, %s)'
    
    data = json.loads(request.body)
    today = datetime.today()
    today = today.strftime('%Y%m%d__%H%M%S')+'1'
    val = (today, datetime.today(), data['username'], data['title'], data['content'])
    print(data)
    cursor.execute(sql, val)
    myDB.commit()
    return JsonResponse({'message': 'OK'})


@csrf_exempt
def post(request):
    rowDict = list()
    myDB = mysql.connector.connect(
        host='localhost',
        user='StudyTS',
        passwd='qwer1234',
        database='StudyTS'
    )
    cursor = myDB.cursor(dictionary=True)
    sql = 'SELECT * FROM post'
    cursor.execute(sql)

    return JsonResponse(cursor.fetchall(), safe=False)
