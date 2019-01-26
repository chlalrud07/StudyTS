import os
from django.contrib.auth.models import User
from django.db import models


def file_path(instance, filename):
    return '{0}/POST/{1}'.format(instance.author.id, filename)


class Post(models.Model):
    author = models.ForeignKey(User, on_delete=models.CASCADE)
    title = models.TextField(max_length=100, null=False)
    content = models.TextField(max_length=500, null=True)
    created_at = models.DateTimeField(auto_now_add=True)
    attached = models.FileField(upload_to=file_path, null=True)

    def delete(self, *args, **kwargs):
        self.attached.delete()
        super(Post, self).delete(*args, **kwargs)

    def filename(self):
        return os.path.basename(self.attached.name)
