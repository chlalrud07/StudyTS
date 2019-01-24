from django.shortcuts import render
from django.http import JsonResponse, HttpResponse
from django.views.decorators.csrf import csrf_exempt
from django.contrib.auth import login

import json
import hashlib

from StudyTS.db_connector import MysqlConnect


@csrf_exempt
def signin(request):
    SQL = 'SELECT * FROM identification'
    connector = MysqlConnect()
    cursor = connector.get_cursor()
    cursor.execute(SQL)

    data = json.loads(request.body)
    password = password_encrypt(data['password'])

    for row in cursor.fetchall():
        if row['username'] == data['username'] and row['password'] == password:
            if row['user_num'] == 0:
                row.update({'permission': 'admin'})
            else:
                row.update(({'permission': 'user'}))
            connector.close()
            row.pop('password')
            row.update({'message': 'Exist'})
            return JsonResponse(row)
    return JsonResponse({'message': 'NotFound'})


@csrf_exempt
def signup(request):
    SQL = 'INSERT INTO identification(username, password, user_num, nickname) VALUES (%s, %s, %s, %s)'
    connector = MysqlConnect()
    cursor = connector.get_cursor()

    data = json.loads(request.body)
    password = password_encrypt(data['password'])

    register_sql_update = 'INSERT INTO register_num(temp) VALUES (2)'
    cursor.execute(register_sql_update)
    cursor.execute('SELECT number FROM register_num')
    num = cursor.fetchall()[-1]

    val = (data['username'], password, int(num['number']), data['nickname'])
    cursor.execute(SQL, val)

    connector.commit()
    return JsonResponse({'message': 'Success'})


@csrf_exempt
def check(request):
    SQL = 'SELECT username FROM identification WHERE username = %s'
    connector = MysqlConnect()
    cursor = connector.get_cursor()

    data = json.loads(request.body)

    val = (data['username'],)
    cursor.execute(SQL, val)
    return JsonResponse({'message': 'NotExist'})


def password_encrypt(password):
    temp = hashlib.sha512()
    temp.update(password.encode('utf-8'))
    return temp.hexdigest()

