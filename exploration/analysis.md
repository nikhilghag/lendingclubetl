Findings 

1.  Higher Interest Rate for lower grades. Grade A will have lower interest rate
    compared to Grade G
    Workbook : GradeInterestRate.twbx
    Can also be viewed here https://public.tableau.com/profile/nikhil.ghag#!/vizhome/LendingClub_15528420694410/InterestRate
    
    Query:
    select grade,min(int_rate), max(int_rate)
    from lendingclub.loan_details
    group by grade
    
    
2.  State with higher population will have more members
    Workbook : LendingClubMembersPopulation.twbx
    Can also be viewed here https://public.tableau.com/profile/nikhil.ghag#!/vizhome/LendingClub_15528420694410/MembersStateWise
    
    https://simple.wikipedia.org/wiki/List_of_U.S._states_by_population
    
    Query:
    select addr_state,count(distinct member_id)
    from lendingclub.loan_details
    group by addr_state