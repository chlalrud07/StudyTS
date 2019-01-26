from django.contrib.auth.models import User
from django.shortcuts import render, redirect
from django.http import JsonResponse, HttpResponse
from django.views.decorators.csrf import csrf_exempt
from django.core.files import File

from Server.db_connector import MysqlConnect
from .models import Post


@csrf_exempt
def index(request):
    connector = MysqlConnect()
    cursor = connector.get_cursor()
    SQL = 'SELECT * FROM community_post'
    cursor.execute(SQL)
    connector.close()
    return JsonResponse(cursor.fetchall(), safe=False)


def new(request, username):
    try:
        user = User.objects.get(email=username)
        if request.method == 'POST':
            post = Post()
            post.author = user
            post.title = request.POST.get('title')
            post.content = request.POST.get('content')
            post.attached = request.FILES.get('attached')
            post.save()
            return redirect('community:finish')
    except:
        return redirect('community:error')
    return render(request, 'community/new.html')


@csrf_exempt
def detail(request, id):
    try:
        post = Post.objects.get(pk=id)
    except:
        return redirect('community:error')
    return render(request, 'community/detail.html', {'post': post})


@csrf_exempt
def finish(request):
    return HttpResponse('')


@csrf_exempt
def error(request):
    return HttpResponse('')


