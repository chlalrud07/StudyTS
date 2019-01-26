from django.shortcuts import render
from django.http import JsonResponse, HttpResponse
from django.views.decorators.csrf import csrf_exempt
from django.contrib.auth.models import User
from django.contrib import auth

from Server.db_connector import MysqlConnect
import json


@csrf_exempt
def signin(request):
    data = json.loads(request.body)
    try:
        user = User.objects.get(username=data['username'])
        if user.check_password(data['password']):
            if user.is_superuser:
                return JsonResponse({'permission': 'admin', 'message': 'Exist'})
            else:
                return JsonResponse({'permission': 'user', 'message': 'Exist'})
    except User.DoesNotExist:
        pass
    return JsonResponse({'message': 'NotFound'})


@csrf_exempt
def signup(request):
    data = json.loads(request.body)

    username = data['username']
    email = data['username']
    password = data['password']
    nickname = data['nickname']
    try:
        user = User.objects.create_user(username, email, password)
        user.save()
        connector = MysqlConnect()
        cursor = connector.get_cursor()
        sql = "insert into user_info values ((select id from auth_user where email=%s), %s)"
        val = (email, nickname)
        cursor.execute(sql, val)
        connector.commit()
        connector.close()
    except:
        return JsonResponse({'message': 'Failed'})
    return JsonResponse({'message': 'Success'})


@csrf_exempt
def check(request):
    data = json.loads(request.body)
    try:
        user = User.objects.get(username=data['username'])
        return JsonResponse({'message': 'Exist'})
    except User.DoesNotExist:
        pass
    return JsonResponse({'message': 'NotExist'})
