from locust import HttpUser, TaskSet, task, constant_pacing
import urllib3

urllib3.disable_warnings(urllib3.exceptions.InsecureRequestWarning)

class UserTaskSet(TaskSet):
    # @task(random.randint(1,10))
    @task
    def test(self):
        self.client.get(url="/1", verify=False)

class WebsiteUser(HttpUser):
    tasks = [UserTaskSet]
    wait_time = constant_pacing(1)
    host = 'http://localhost:8080'

