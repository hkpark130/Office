INSERT INTO categories (id, name, img) values (1, '노트북', 'notebook.png');
INSERT INTO categories (id, name, img) values (2, '서버', 'server.png');
INSERT INTO categories (id, name, img) values (3, '모니터', 'monitor.png');
INSERT INTO categories (id, name, img) values (4, '프린터', 'printer.png');

INSERT INTO DEPARTMENTS (name) VALUES ('경영지원부');
INSERT INTO DEPARTMENTS (name) VALUES ('제품2팀');

insert into users (auth,department_id,username,id) values ('Admin',null,'test',1);

INSERT INTO PROJECTS (name) VALUES ('본사');
INSERT INTO PROJECTS (name) VALUES ('농협');

INSERT INTO devices (id, user_id, manage_dep, spec, price, model, description, tag, company, sn, status, purpose, purchase_date, category_id, project_id)
values ('DIR-N-101', null, 1, 'RAM: 16G CPU: 8core', 1000, 'LSBX2433', '부팅 느림', 'test', 'SAMSUNG', 'ND6179NBK', true, '개발', '2021-09-01', 1, 1);
INSERT INTO devices (id, user_id, manage_dep, spec, price, model, description, tag, company, sn, status, purpose, purchase_date, category_id, project_id)
values ('DIR-N-102', null, 1, 'RAM: 8G CPU: 4core', 1000, 'LSBX2434', 'OS 설치 필요', 'test', 'LG', 'ND6179NBJ', true, '사무', '2021-09-02', 2, 1);
INSERT INTO devices (id, user_id, manage_dep, spec, price, model, description, tag, company, sn, status, purpose, purchase_date, category_id, project_id)
values ('DIR-N-103', null, 1, 'RAM: 16G CPU: 8core', 1000, 'LSBX2433', '부팅 느림 부팅 느림 부팅 느림 부팅 느림 부팅 느림 부팅 느림 부팅 느림 부팅 느림 부팅 느림 부팅 느림 부팅 느림 부팅 느림 부팅 느림 부팅 느림 부팅 느림 부팅 느림 부팅 느림 부팅 느림 부팅 느림 부팅 느림 부팅 느림 부팅 느림 부팅 느림 부팅 느림 부팅 느림 부팅 느림 부팅 느림 부팅 느림 부팅 느림 부팅 느림 부팅 느림 부팅 느림 부팅 느림 부팅 느림 부팅 느림 부팅 느림 부팅 느림 부팅 느림 부팅 느림 부팅 느림 부팅 느림 ', 'test', 'SAMSUNG', 'ND6179NBK', true, '개발', '2021-09-01', 1, 1);
INSERT INTO devices (id, user_id, manage_dep, spec, price, model, description, tag, company, sn, status, purpose, purchase_date, category_id, project_id)
values ('DIR-N-104', null, 2, 'RAM: 16G CPU: 8core', 1000, 'LSBX2433', '부팅 느림', 'test', 'SAMSUNG', 'ND6179NBK', true, '개발', '2021-09-01', 1, 1);
INSERT INTO devices (id, user_id, manage_dep, spec, price, model, description, tag, company, sn, status, purpose, purchase_date, category_id, project_id)
values ('DIR-N-105', null, 2, 'RAM: 16G CPU: 8core', 1000, 'LSBX2433', '부팅 느림', 'test', 'SAMSUNG', 'ND6179NBK', true, '개발', '2021-09-01', 1, 1);
INSERT INTO devices (id, user_id, manage_dep, spec, price, model, description, tag, company, sn, status, purpose, purchase_date, category_id, project_id)
values ('DIR-N-106', null, 2, 'RAM: 16G CPU: 8core', 1000, 'LSBX2433', '부팅 느림', 'test', 'SAMSUNG', 'ND6179NBK', true, '개발', '2021-09-01', 1, 1);
INSERT INTO devices (id, user_id, manage_dep, spec, price, model, description, tag, company, sn, status, purpose, purchase_date, category_id, project_id)
values ('DIR-N-107', null, 1, 'RAM: 16G CPU: 8core', 1000, 'LSBX2433', '부팅 느림', 'test', 'SAMSUNG', 'ND6179NBK', true, '개발', '2021-09-01', 1, null);
INSERT INTO devices (id, user_id, manage_dep, spec, price, model, description, tag, company, sn, status, purpose, purchase_date, category_id, project_id)
values ('DIR-N-108', null, 1, 'RAM: 16G CPU: 8core', 1000, 'LSBX2433', '부팅 느림', 'test', 'SAMSUNG', 'ND6179NBK', true, '개발', '2021-09-01', 1, null);
INSERT INTO devices (id, user_id, manage_dep, spec, price, model, description, tag, company, sn, status, purpose, purchase_date, category_id, project_id)
values ('DIR-N-109', null, 1, 'RAM: 16G CPU: 8core', 1000, 'LSBX2433', '부팅 느림', 'test', 'SAMSUNG', 'ND6179NBK', true, '개발', '2021-09-01', 2, 1);
INSERT INTO devices (id, user_id, manage_dep, spec, price, model, description, tag, company, sn, status, purpose, purchase_date, category_id, project_id)
values ('DIR-N-110', null, 1, 'RAM: 16G CPU: 8core', 1000, 'LSBX2433', '부팅 느림', 'test', 'SAMSUNG', 'ND6179NBK', false, '개발', '2021-09-01', 1, 1);
INSERT INTO devices (id, user_id, manage_dep, spec, price, model, description, tag, company, sn, status, purpose, purchase_date, category_id, project_id)
values ('DIR-N-111', null, 1, '품질 최상', 10000, 'LSBX2', '부팅', 'test', 'SAMSUNG', 'ND6K', true, '개발', '2021-09-01', 3, 1);
INSERT INTO devices (id, user_id, manage_dep, spec, price, model, description, tag, company, sn, status, purpose, purchase_date, category_id, project_id)
values ('DIR-N-112', null, 1, 'RAM: 2G', 1000, 'LSBXTEST', '폐기함', 'test', 'SAMSUNG', 'ND6179NBK', false, '개발', '2021-09-01', 1, 1);
INSERT INTO devices (id, user_id, manage_dep, spec, price, model, description, tag, company, sn, status, purpose, purchase_date, category_id, project_id)
values ('DIR-N-113', 1, 1, 'RAM: 16G CPU: 8core', 1000, 'LSBX2433', '사용중인 기기', 'test', 'SAMSUNG', 'ND6179NBK', true, '개발', '2021-09-01', 1, 1);
INSERT INTO devices (id, user_id, manage_dep, spec, price, model, description, tag, company, sn, status, purpose, purchase_date, category_id, project_id)
values ('DIR-N-114', 1, 1, 'RAM: 16G CPU: 8core', 1000, 'LSBX24', '폐기한 기기', 'test', 'SAMSUNG', 'ND9NBK', true, '개발', '2021-09-02', 1, 1);

INSERT INTO APPROVALS (APPROVER_ID, USER_ID, APPROVAL_INFO, REASON, CREATED_DATE) values (1, 1, '승인완료', 'test용', '2021-08-01');
INSERT INTO APPROVALS (APPROVER_ID, USER_ID, APPROVAL_INFO, REASON) values (null, 1, '승인대기', 'test용');
INSERT INTO APPROVALS (APPROVER_ID, USER_ID, APPROVAL_INFO, REASON) values (1, 1, '승인완료', 'test용2');
INSERT INTO APPROVALS (APPROVER_ID, USER_ID, APPROVAL_INFO, REASON) values (1, 1, '승인완료', 'test용 폐기');
INSERT INTO APPROVALS (APPROVER_ID, USER_ID, APPROVAL_INFO, REASON, CREATED_DATE) values (null, 1, '승인대기', 'test용', '2021-09-01');

INSERT INTO APPROVAL_DEVICES (id, device_id, img, type) values (1, 'DIR-N-101', 'notebook.png', '구매');
INSERT INTO APPROVAL_DEVICES (id, device_id, img, type) values (2, 'DIR-N-102', 'server.png', '폐기');
INSERT INTO APPROVAL_DEVICES (id, device_id, img, type) values (3, 'DIR-N-113', 'notebook.png', '대여');
INSERT INTO APPROVAL_DEVICES (id, device_id, img, type) values (4, 'DIR-N-114', 'notebook.png', '폐기');
INSERT INTO APPROVAL_DEVICES (id, device_id, img, type) values (5, 'DIR-N-101', 'notebook.png', '반납');
