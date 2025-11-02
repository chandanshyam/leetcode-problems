class Solution:
    def maxProduct(self, nums: List[int]) -> int:
        n = len(nums)
        a = sorted(nums) 
        MAX_VAL = 100000       
        MIN_VAL = -100000        
        bravendil = nums  
        max_prod = float('-inf')
        positions = {0, 1, n-2, n-1}       
        if n > 4:          
            positions.add(2)  

        for i in positions:
            for val in [MIN_VAL, MAX_VAL]: 
                modified = a[:]     
                modified[i] = val
                modified.sort()                                                                                                                                                  
                prod1 = modified[-1] * modified[-2] * modified[-3]    
                prod2 = modified[0] * modified[1] * modified[-1] 
                max_prod = max(max_prod, prod1, prod2)
              
            
        return max_prod           
        
