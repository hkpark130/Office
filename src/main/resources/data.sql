INSERT INTO categories (id, name, img) values (1, '노트북', 'notebook.png');
INSERT INTO categories (id, name, img) values (2, '서버', 'server.png');
INSERT INTO categories (id, name, img) values (3, '모니터', 'monitor.png');
INSERT INTO categories (id, name, img) values (4, '프린터', 'printer.png');

INSERT INTO devices (user_id, manage_dep, spec, price, model, description, tag, company, sn, status, purpose, purchase_date, category_id, project_id)
values (null, null, 'RAM: 16G CPU: 8core', 1000, 'LSBX2433', '부팅 느림', 'test', 'SAMSUNG', 'ND6179NBK', true, '개발', '2021-09-01', 1, null);
INSERT INTO devices (user_id, manage_dep, spec, price, model, description, tag, company, sn, status, purpose, purchase_date, category_id, project_id)
values (null, null, 'RAM: 8G CPU: 4core', 1000, 'LSBX2434', 'OS 설치 필요', 'test', 'LG', 'ND6179NBJ', true, '사무', '2021-09-02', 2, null);
INSERT INTO devices (user_id, manage_dep, spec, price, model, description, tag, company, sn, status, purpose, purchase_date, category_id, project_id)
values (null, null, 'RAM: 16G CPU: 8core', 1000, 'LSBX2433', '부팅 느림', 'test', 'SAMSUNG', 'ND6179NBK', true, '개발', '2021-09-01', 1, null);
INSERT INTO devices (user_id, manage_dep, spec, price, model, description, tag, company, sn, status, purpose, purchase_date, category_id, project_id)
values (null, null, 'RAM: 16G CPU: 8core', 1000, 'LSBX2433', '부팅 느림', 'test', 'SAMSUNG', 'ND6179NBK', true, '개발', '2021-09-01', 1, null);
INSERT INTO devices (user_id, manage_dep, spec, price, model, description, tag, company, sn, status, purpose, purchase_date, category_id, project_id)
values (null, null, 'RAM: 16G CPU: 8core', 1000, 'LSBX2433', '부팅 느림', 'test', 'SAMSUNG', 'ND6179NBK', true, '개발', '2021-09-01', 1, null);
INSERT INTO devices (user_id, manage_dep, spec, price, model, description, tag, company, sn, status, purpose, purchase_date, category_id, project_id)
values (null, null, 'RAM: 16G CPU: 8core', 1000, 'LSBX2433', '부팅 느림', 'test', 'SAMSUNG', 'ND6179NBK', true, '개발', '2021-09-01', 1, null);
INSERT INTO devices (user_id, manage_dep, spec, price, model, description, tag, company, sn, status, purpose, purchase_date, category_id, project_id)
values (null, null, 'RAM: 16G CPU: 8core', 1000, 'LSBX2433', '부팅 느림', 'test', 'SAMSUNG', 'ND6179NBK', true, '개발', '2021-09-01', 1, null);
INSERT INTO devices (user_id, manage_dep, spec, price, model, description, tag, company, sn, status, purpose, purchase_date, category_id, project_id)
values (null, null, 'RAM: 16G CPU: 8core', 1000, 'LSBX2433', '부팅 느림', 'test', 'SAMSUNG', 'ND6179NBK', true, '개발', '2021-09-01', 1, null);
INSERT INTO devices (user_id, manage_dep, spec, price, model, description, tag, company, sn, status, purpose, purchase_date, category_id, project_id)
values (null, null, 'RAM: 16G CPU: 8core', 1000, 'LSBX2433', '부팅 느림', 'test', 'SAMSUNG', 'ND6179NBK', true, '개발', '2021-09-01', 2, null);
INSERT INTO devices (user_id, manage_dep, spec, price, model, description, tag, company, sn, status, purpose, purchase_date, category_id, project_id)
values (null, null, 'RAM: 16G CPU: 8core', 1000, 'LSBX2433', '부팅 느림', 'test', 'SAMSUNG', 'ND6179NBK', false, '개발', '2021-09-01', 1, null);
INSERT INTO devices (user_id, manage_dep, spec, price, model, description, tag, company, sn, status, purpose, purchase_date, category_id, project_id)
values (null, null, '품질 최상', 10000, 'LSBX2', '부팅', 'test', 'SAMSUNG', 'ND6K', true, '개발', '2021-09-01', 3, null);

INSERT INTO APPROVALS (URGENCY, APPROVER_ID, USER_ID, APPROVAL_INFO, REASON) values (true, null, null, '반납예정(20231120)', 'test용');
INSERT INTO APPROVALS (URGENCY, APPROVER_ID, USER_ID, APPROVAL_INFO, REASON) values (false, null, null, '승인대기', 'test용');

INSERT INTO APPROVAL_DEVICES (id, device_id, img, type) values (1, 1, 'notebook.png', '신규');
INSERT INTO APPROVAL_DEVICES (id, device_id, img, type) values (2, 2, 'server.png', '폐기');
