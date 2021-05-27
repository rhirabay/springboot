from locust import HttpUser, TaskSet, task, constant_pacing, events
import urllib3
import uuid
import logging

urllib3.disable_warnings(urllib3.exceptions.InsecureRequestWarning)

class UserTaskSet(TaskSet):
    @task
    def test(self):
        self.client.get(url="/randomStatus", verify=False, context={"uuid": str(uuid.uuid4())})

class WebsiteUser(HttpUser):
    tasks = [ UserTaskSet ]
    wait_time = constant_pacing(1)
    host = 'http://localhost:8080'

@events.request.add_listener
def logging_response(request_type, name, response_time, response_length, response,
              context, exception, **kwargs):
    if not response.ok:
        try:
            uuid = context["no_data"]
        except KeyError:
            uuid = ''
        logging.info(f"[request event] path: {name} status: {response.status_code} body: {response.text} uuid: {uuid}")