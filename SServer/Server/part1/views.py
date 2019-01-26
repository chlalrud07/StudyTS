from django.shortcuts import render
from django.http import JsonResponse, HttpResponse
from django.views.decorators.csrf import csrf_exempt

from Server.db_connector import MysqlConnect



@csrf_exempt
def index(request):

    connector = MysqlConnect()
    cursor = connector.get_cursor()
    SQL = 'SELECT * FROM item'
    cursor.execute(SQL)
    connector.close()
    return JsonResponse(cursor.fetchall(), safe=False)
