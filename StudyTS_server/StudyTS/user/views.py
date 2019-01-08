from django.http import JsonResponse, HttpResponse
from django.views.decorators.csrf import csrf_exempt

import json
import mysql.connector
import hashlib


@csrf_exempt
def signin(request):
    myDB = mysql.connector.connect(
        host='localhost',
        user='StudyTS',
        passwd='qwer1234',
        database='StudyTS'
    )
    cursor = myDB.cursor(dictionary=True)
    cursor.execute('SELECTOR username, password FROM identification')

    data = json.loads(request.body)
    password = encrypt_password(data['password'])

    for row in cursor.fetchall():
        if data['email'] == row['username'] and password == row['password']:
            cursor.close()
            return JsonResponse({'message':'Exist'})
    return JsonResponse({'message':'NotExist'})


@csrf_exempt
def signup(request):
    data = json.loads(request.body)
    myDB = mysql.connector.connect(
        host='localhost',
        user='StudyTS',
        passwd='qwer1234',
        database='StudyTS'
    )
    cursor = myDB.cursor(dictionary=True)
    sql = 'INSERT INTO identification(username, password, user_num, nickname) VALUES (%s, %s, %s, %s)'

    val = (data['email'], encrypt_password(data['password']), int(1), data['nickname'])
    cursor.execute(sql, val)
    myDB.commit()
    return JsonResponse(request)


@csrf_exempt
def check_email(request):
    data = json.loads(request.body)
    myDB = mysql.connector.connect(
        host='localhost',
        user='StudyTS',
        passwd='qwer1234',
        database='StudyTS'
    )
    cursor = myDB.cursor(dictionary=True)
    sql = 'SELECT username FROM identification'
    cursor.execute(sql)

    for row in cursor.fetchall():
        if data['email'] == row['username']:
            cursor.close()
            return JsonResponse({'message':'Exist'})
    cursor.close()
    return JsonResponse({'message':'NotExist'})


def encrypt_password(password):
    temp = hashlib.sha256()
    temp.update(password.encode('utf-8'))
    return temp.hexdigest()
