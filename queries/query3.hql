select purpose, count(member_id) cnt
from lendingclub.loan_details 
where loan_status not in ('Fully Paid','Issued','Current') 
group by purpose
order by 2 desc
limit 1;