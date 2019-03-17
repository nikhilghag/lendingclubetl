select round(count(member_id) / 
(   
    select count(member_id) from lendingclub.loan_details
)
,2) * 100 as GoodLoanPercentage
from lendingclub.loan_details 
where loan_status in ('Fully Paid','Issued','Current') 
group by term;