import pymysql


class MysqlConnect:
    connector = pymysql

    def __init__(self):
        self.connector = pymysql.connect(
            host='localhost',
            user='StudyTS',
            password='qwer1234',
            db='StudyTS',
            charset='utf8'
        )

    def close(self):
        self.connector.close()

    def get_cursor(self):
        cursor = self.connector.cursor(pymysql.cursors.DictCursor)
        return cursor

    def commit(self):
        self.connector.commit()

