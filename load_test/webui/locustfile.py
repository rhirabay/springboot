from locust import HttpUser, TaskSet, task, constant_pacing, events
from flask import request
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

@events.init.add_listener
def on_locust_init(web_ui, **kw):
    @web_ui.app.route("/my_custom_route")
    def my_custom_route():
        return request.args.get('value', '')

