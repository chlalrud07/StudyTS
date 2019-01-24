from django.shortcuts import render
from django.http import JsonResponse, HttpResponse
from django.views.decorators.csrf import csrf_exempt

from StudyTS.db_connector import MysqlConnect


@csrf_exempt
def index(request):
    connector = MysqlConnect()
    cursor = connector.get_cursor()
    SQL = 'SELECT * FROM post'
    cursor.execute(SQL)
    return JsonResponse(cursor.fetchall(), safe=False)


def new(request):
    print(request.user)
    if request.method == 'POST':
        connector = MysqlConnect()
        cursor = connector.get_cursor()
        connector.close()
        return HttpResponse("ddd")
    return render(request, 'community/new.html')


@csrf_exempt
def detail(request):
    return render(request, 'community/new.html')

