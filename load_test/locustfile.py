from locust import HttpUser, TaskSet, task, constant_pacing
import urllib3
import random

urllib3.disable_warnings(urllib3.exceptions.InsecureRequestWarning)

class UserTaskSet(TaskSet):
    # @task(random.randint(1,10))
    @task
    def test(self):
        self.client.get(url="/1", verify=False)

class UserTaskSet2(TaskSet):
    # @task(random.randint(1,10))
    @task
    def test2(self):
        self.client.get(url="/2", verify=False)

class WebsiteUser(HttpUser):
    tasks = {
        UserTaskSet: random.randint(1,10),
        UserTaskSet2: random.randint(1,10)
    }
    print(tasks)
    weight = random.randint(1,10)
    wait_time = constant_pacing(1)
    host = 'http://localhost:8080'

