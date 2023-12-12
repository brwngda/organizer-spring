## Organizer Application

### Description
Organizer is a project management application that allows you to easily create a work schedule and track progress. The
application is based on the Jira software.

### Technologies used in the project:
- Spring Boot,
- JPA,
- Hibernate,
- MySQL,
- REST API,
- RabbitMq,
- 

### Features:

- [x] user login and registration
- [x] adding / modifying projects and sprints
- [x] delegating tasks, setting task priorities
- [x] changing task progress


### Screenshots:

<img alt="App Screenshot" height="186" src="https://private-user-images.githubusercontent.com/119103579/289959980-eb2db1b0-7cdb-4bd1-8514-d8d6b1d18eb0.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTEiLCJleHAiOjE3MDI0MDQ1NTIsIm5iZiI6MTcwMjQwNDI1MiwicGF0aCI6Ii8xMTkxMDM1NzkvMjg5OTU5OTgwLWViMmRiMWIwLTdjZGItNGJkMS04NTE0LWQ4ZDZiMWQxOGViMC5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBSVdOSllBWDRDU1ZFSDUzQSUyRjIwMjMxMjEyJTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDIzMTIxMlQxODA0MTJaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT1hNjYyN2MxNzliNWRmOGI4YjRjYzM3NzYzODhhYzZiYzdhYWJhODlmMjQxNWU4YWQyNjRlYjY5ZmM4NjVlZThmJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCZhY3Rvcl9pZD0wJmtleV9pZD0wJnJlcG9faWQ9MCJ9.0SBp_WCCie5nHyxXIHKYCr_YBUDKCRiMGztyuIpZ4JI" width="325"/>
<img alt="App Screenshot" height="186" src="https://private-user-images.githubusercontent.com/119103579/289960018-05a97e12-594b-4a3d-ab59-5d62daa76e47.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTEiLCJleHAiOjE3MDI0MDQ1NTIsIm5iZiI6MTcwMjQwNDI1MiwicGF0aCI6Ii8xMTkxMDM1NzkvMjg5OTYwMDE4LTA1YTk3ZTEyLTU5NGItNGEzZC1hYjU5LTVkNjJkYWE3NmU0Ny5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBSVdOSllBWDRDU1ZFSDUzQSUyRjIwMjMxMjEyJTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDIzMTIxMlQxODA0MTJaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT1kZDU2YzQ2ZjI5YWNjNTMyYTAyNDg4YmExMThkOWNhNDQ5NGNjZDAwZGE3MzkzOWVmNjcyM2YxYjM5ZjllYzIwJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCZhY3Rvcl9pZD0wJmtleV9pZD0wJnJlcG9faWQ9MCJ9.G073y_XfwMYynmg3EnFlX1VAGv0qG2eoe4QHPJxFiIQ" width="325"/>
<img alt="App Screenshot" height="186" src="https://private-user-images.githubusercontent.com/119103579/289960063-e74801e3-6aec-4a9a-937d-ba0aa1273ed4.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTEiLCJleHAiOjE3MDI0MDQ1NTIsIm5iZiI6MTcwMjQwNDI1MiwicGF0aCI6Ii8xMTkxMDM1NzkvMjg5OTYwMDYzLWU3NDgwMWUzLTZhZWMtNGE5YS05MzdkLWJhMGFhMTI3M2VkNC5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBSVdOSllBWDRDU1ZFSDUzQSUyRjIwMjMxMjEyJTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDIzMTIxMlQxODA0MTJaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT1iOTNjNDhhMzYyMDIxZTFmOTNjZWM5NDJmMTkxMzQ0MDQ2YzFjMTM2YTdmM2M2ZmU0ZDMxOTgxYmRkODg5NjllJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCZhY3Rvcl9pZD0wJmtleV9pZD0wJnJlcG9faWQ9MCJ9.Bct1ZQOt555BH9nu14eVoLyszszzqGX9EkeSgsSij4M" width="325"/>

### Author
@Grzegorz Barwiński


[![portfolio](https://img.shields.io/badge/my_portfolio-000?style=for-the-badge&logo=ko-fi&logoColor=white)](https://www.github.com/brwngda)
[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/grzegorz-barwinski/)