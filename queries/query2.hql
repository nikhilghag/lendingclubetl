select emp_title,count(member_id) as loan_count, 'max loan count'
from lendingclub.loan_details 
where emp_title is not null
group by emp_title
having loan_count = 
(
    select max(loan_count) max from 
    (
        select emp_title,count(member_id) as loan_count 
        from lendingclub.loan_details 
        where emp_title is not null
        group by emp_title
    )tmp1
)
union
select emp_title,count(member_id) as loan_count, 'min loan count'
from lendingclub.loan_details 
where emp_title is not null
group by emp_title
having loan_count = 
(
    select min(loan_count) max from 
    (
        select emp_title,count(member_id) as loan_count 
        from lendingclub.loan_details 
        where emp_title is not null
        group by emp_title
    )tmp1
);