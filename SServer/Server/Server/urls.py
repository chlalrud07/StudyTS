from django.contrib import admin
from django.urls import path, include
from django.conf import settings
from django.conf.urls.static import static

urlpatterns = [
    path('user/', include('user.urls')),
    path('community/', include('community.urls')),
    path('part1/', include('part1.urls')),
    path('part2/', include('part2.urls')),
    path('admin/', admin.site.urls),
] + static(settings.MEDIA_URL, document_root = settings.MEDIA_ROOT)
