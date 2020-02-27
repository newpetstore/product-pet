from locust import HttpLocust, TaskSet, task, between

def index(l):
    l.client.get("/")

def info(l):
    l.client.get("/actuator/info")

class UserTasks(TaskSet):
    tasks = [index, info]

    # but it might be convenient to use the @task decorator
    # @task
    # def page404(self):
    #     self.client.get("/does_not_exist")

class WebsiteUser(HttpLocust):
    """
    Locust user class that does requests to the locust web server running on localhost
    """
    wait_time = between(2, 5)
    task_set = UserTasks
