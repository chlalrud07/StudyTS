from django.urls import path
from . import views


app_name = 'community'
urlpatterns = [
    path('', views.index, name='index'),
    path('detail/<int:id>/', views.detail, name='detail'),
    path('new/<str:username>/', views.new, name='new'),
    path('finish/', views.finish, name='finish'),
    path('error/', views.error, name='error'),
]