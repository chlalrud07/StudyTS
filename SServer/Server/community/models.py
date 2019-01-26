from django.contrib.auth.models import User
from django.db import models
from datetime import datetime

class Post(models.Model):
    author = models.ForeignKey(User, on_delete=models.CASCADE)
    title = models.TextField(max_length=100, null=False)
    content = models.TextField(max_length=500, null=True)
    created_at = models.DateTimeField(default=datetime.today())
    files = models.FileField(upload_to='%Y%m%d/', null=True)
