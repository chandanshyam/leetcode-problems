class Logger:

    def __init__(self):
        self.window = 10
        self.map = {}
        

    def shouldPrintMessage(self, timestamp: int, message: str) -> bool:

        if message not in self.map:
            self.map[message] = timestamp
            return True
        
        if timestamp - self.map[message] < self.window:
            return False
        else:
            self.map[message]=timestamp
            return True

            


        
        


# Your Logger object will be instantiated and called as such:
# obj = Logger()
# param_1 = obj.shouldPrintMessage(timestamp,message)
