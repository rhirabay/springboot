from locust import HttpLocust, TaskSet, task, between, constant

class UserBehavior(TaskSet):
    @task(1)
    def task1(self):
        self.client.get("/ratelimit/task1", verify=False)

    @task(1)
    def task2(self):
        self.client.get("/ratelimit/task2", verify=False)

class WebsiteUser(HttpLocust):
    task_set = UserBehavior
    wait_time = constant(1)