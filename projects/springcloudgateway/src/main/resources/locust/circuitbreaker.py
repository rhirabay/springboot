from locust import HttpLocust, TaskSet, task, between, constant

class UserBehavior(TaskSet):
    @task(1)
    def task2(self):
        self.client.get("/circuitbreaker", params={}, verify=False)

class WebsiteUser(HttpLocust):
    task_set = UserBehavior
    wait_time = constant(0.5)