from django.shortcuts import render
from django.http import JsonResponse, HttpResponse
import json
import pymysql


def signin(request):
    SQL = 'SELECT * FROM identification'
    connector = pymysql.connect(
        host='localhost',
        user='StudyTS',
        password='qwer1234',
        db='StudyTS',
        charset='utf8'
    )
    cursor = connector.cursor()
    cursor.execute(SQL)

    print(type(cursor.fetchall()))
    connector.close()
    return HttpResponse('Hi')


def signup(request):
    return render(request, 'community/new.html')


def check(request, username):
    return render(request, 'community/new.html')

