INSERT INTO categories (name, img) values ('노트북', 'notebook.png');
INSERT INTO categories (name, img) values ('서버', 'server.png');
INSERT INTO categories (name, img) values ('모니터', 'monitor.png');
INSERT INTO categories (name, img) values ('PC 본체', 'pc.png');

INSERT INTO DEPARTMENTS (name, code) VALUES ('경영지원부', '0052');
INSERT INTO DEPARTMENTS (name, code) VALUES ('영업본부', '0049');
INSERT INTO DEPARTMENTS (name, code) VALUES ('제품1팀(유지보수)', '0032');
INSERT INTO DEPARTMENTS (name, code) VALUES ('제품1팀(그외)', '0021');
INSERT INTO DEPARTMENTS (name, code) VALUES ('제품2팀(유지보수)', '0037');
INSERT INTO DEPARTMENTS (name, code) VALUES ('제품2팀(그외)', '0108');
INSERT INTO DEPARTMENTS (name, code) VALUES ('제품3팀', '0109');
INSERT INTO DEPARTMENTS (name, code) VALUES ('제품4팀(유지보수)', '0036');
INSERT INTO DEPARTMENTS (name, code) VALUES ('제품4팀(그외)', '0024');
INSERT INTO DEPARTMENTS (name, code) VALUES ('기술영업/컨설팅', '0173');
INSERT INTO DEPARTMENTS (name, code) VALUES ('데이터플랫폼사업부', '0056');
INSERT INTO DEPARTMENTS (name, code) VALUES ('SI사업부', '0054');

insert into users (auth,department_id,username,id) values ('Admin',null,'test',1);

INSERT INTO PROJECTS (name, code) VALUES ('본사','0001');
INSERT INTO PROJECTS (name, code) VALUES ('신한은행 The Next-코어및디지털기반영역_전자금융','0011');
INSERT INTO PROJECTS (name, code) VALUES ('신한은행 The Next - 대외대행','0102');
INSERT INTO PROJECTS (name, code) VALUES ('올원뱅크 차세대플랫폼 구축','0151');
INSERT INTO PROJECTS (name, code) VALUES ('저축은행중앙회 통합채널모니터링시스템 도입', '0152');
INSERT INTO PROJECTS (name, code) VALUES ('교원 차세대 영업관리시스템 구축 대외인터페이스(FEP) 개발', '0156');
INSERT INTO PROJECTS (name, code) VALUES ('신협중앙회 모바일브랜치 시스템 구축', '0160');
INSERT INTO PROJECTS (name, code) VALUES ('삼성카드 마이데이터 구축(에스코어)', '0169');
INSERT INTO PROJECTS (name, code) VALUES ('우리카드 인터페이스(EAI) 재구축', '0170');
INSERT INTO PROJECTS (name, code) VALUES ('일렉링크(에스트래픽) 업무 개발', '0174');
INSERT INTO PROJECTS (name, code) VALUES ('롯데캐피탈 L-ONE 차세대 시스템 구축(MCI,EAI)', '0175');
INSERT INTO PROJECTS (name, code) VALUES ('농협은행 MCA웹고도화', '0176');
INSERT INTO PROJECTS (name, code) VALUES ('삼성카드 마이데이터 정보제공범위 확대', '0177');
INSERT INTO PROJECTS (name, code) VALUES ('신한은행 통합검색포털 구축', '0178');
INSERT INTO PROJECTS (name, code) VALUES ('저축은행중앙회 통합채널 솔루션 운영', '0181');
INSERT INTO PROJECTS (name, code) VALUES ('신용정보원 마이데이터 중계플랫폼 운영 및 추가기능 개발', '0182');
INSERT INTO PROJECTS (name, code) VALUES ('신한은행 콜센터 인프라 재구축', '0186');
INSERT INTO PROJECTS (name, code) VALUES ('농협은행 MCI iManager CPM 모니터링 연동', '0190');
INSERT INTO PROJECTS (name, code) VALUES ('군인공제회 차세대 회원업무시스템 대외연계 구축', '0191');
INSERT INTO PROJECTS (name, code) VALUES ('신한은행 유니버셜 간편앱 대응 API개발', '0194');
INSERT INTO PROJECTS (name, code) VALUES ('KAIT eSIM 도입에 따른 동일명의 서비스 추가기능', '0195');
INSERT INTO PROJECTS (name, code) VALUES ('롯데카드 인터페이스 고도화 사업', '0196');
INSERT INTO PROJECTS (name, code) VALUES ('교직원공제회 ESB 통합시스템 구축', '0198');
INSERT INTO PROJECTS (name, code) VALUES ('현대카드 통합 모니터링', '0199');
INSERT INTO PROJECTS (name, code) VALUES ('현대캐피탈 Observability 구축', '0200');
INSERT INTO PROJECTS (name, code) VALUES ('KAIT 공공 마이데이터 중계시스템 구축', '0202');
INSERT INTO PROJECTS (name, code) VALUES ('NH농협/디지털금융 플랫폼 전환(아키텍처/MCI)', '0204');
INSERT INTO PROJECTS (name, code) VALUES ('스마트로 OAuth인증시스템 구축', '0205');
INSERT INTO PROJECTS (name, code) VALUES ('스카니아 파이낸스 차세대 시스템 구축', '0206');
INSERT INTO PROJECTS (name, code) VALUES ('저축은행중앙회 MCI 미통합저축은행 분리 프로젝트', '0207');
INSERT INTO PROJECTS (name, code) VALUES ('IBK연금보험 대내외 연계', '0208');
INSERT INTO PROJECTS (name, code) VALUES ('한화생명 마이데이터 솔루션 네트워크 방식 변경 커스터마이징', '0209');
INSERT INTO PROJECTS (name, code) VALUES ('NH농협카드 통합 디지털 플랫폼 구축_MCI,NMS', '0210');
INSERT INTO PROJECTS (name, code) VALUES ('우리 New WON뱅킹 구축', '0211');
INSERT INTO PROJECTS (name, code) VALUES ('BNK캐피탈 CruzLink FEP 구축', '0212');
INSERT INTO PROJECTS (name, code) VALUES ('신한은행 대직원용 ChatGPT 시스템 연계 개발', '0213');
INSERT INTO PROJECTS (name, code) VALUES ('DGB캐피탈 금융결제원 이중화 연계 기술지원', '0214');
INSERT INTO PROJECTS (name, code) VALUES ('라이나생명 네이버페이 연동 개발', '0215');
INSERT INTO PROJECTS (name, code) VALUES ('신한투자증권 차세대(임시)', '0216');
INSERT INTO PROJECTS (name, code) VALUES ('농협손해보험 공공 마이데이터(임시)', '0217');
INSERT INTO PROJECTS (name, code) VALUES ('MG손해보험 플랫폼 보험비교 추천서비스 API연동구축', '0218');
INSERT INTO PROJECTS (name, code) VALUES ('신한금융그룹 공통 AI컨텍센터 플랫폼 구축', '0219');
INSERT INTO PROJECTS (name, code) VALUES ('농협(은행)금융상품 비교 플랫폼 구축', '0220');

INSERT INTO devices (id, user_id, manage_dep, spec, price, model, description, company, sn, status, is_usable, purpose, purchase_date, category_id, project_id)
values ('DIR-N-101', null, 1, 'RAM: 16G CPU: 8core', 1000, 'LSBX2433', '부팅 느림', 'SAMSUNG', 'ND6179NBK', '정상', true, '개발', '2021-09-01', 1, 1);
INSERT INTO devices (id, user_id, manage_dep, spec, price, model, description, company, sn, status, is_usable, purpose, purchase_date, category_id, project_id)
values ('DIR-N-102', null, 1, 'RAM: 8G CPU: 4core', 1000, 'LSBX2434', 'OS 설치 필요', 'LG', 'ND6179NBJ', '정상', true, '사무', '2021-09-02', 2, 1);
INSERT INTO devices (id, user_id, manage_dep, spec, price, model, description, company, sn, status, is_usable, purpose, purchase_date, category_id, project_id)
values ('DIR-N-103', null, 1, 'RAM: 16G CPU: 8core', 1000, 'LSBX2433', '부팅 느림 부팅 느림', 'SAMSUNG', 'ND6179NBK', '정상', true, '개발', '2021-09-01', 1, 1);
INSERT INTO devices (id, user_id, manage_dep, spec, price, model, description, company, sn, status, is_usable, purpose, purchase_date, category_id, project_id)
values ('DIR-N-104', null, 2, 'RAM: 16G CPU: 8core', 1000, 'LSBX2433', '부팅 느림', 'SAMSUNG', 'ND6179NBK', '정상', true, '개발', '2021-09-01', 1, 1);
INSERT INTO devices (id, user_id, manage_dep, spec, price, model, description, company, sn, status, is_usable, purpose, purchase_date, category_id, project_id)
values ('DIR-N-105', null, 2, 'RAM: 16G CPU: 8core', 1000, 'LSBX2433', '부팅 느림', 'SAMSUNG', 'ND6179NBK', '정상', true, '개발', '2021-09-01', 1, 1);
INSERT INTO devices (id, user_id, manage_dep, spec, price, model, description, company, sn, status, is_usable, purpose, purchase_date, category_id, project_id)
values ('DIR-N-106', null, 2, 'RAM: 16G CPU: 8core', 1000, 'LSBX2433', '부팅 느림', 'SAMSUNG', 'ND6179NBK', '정상', true, '개발', '2021-09-01', 1, 1);
INSERT INTO devices (id, user_id, manage_dep, spec, price, model, description, company, sn, status, is_usable, purpose, purchase_date, category_id, project_id)
values ('DIR-N-107', null, 1, 'RAM: 16G CPU: 8core', 1000, 'LSBX2433', '부팅 느림', 'SAMSUNG', 'ND6179NBK', '노후', true, '개발', '2021-09-01', 1, null);
INSERT INTO devices (id, user_id, manage_dep, spec, price, model, description, company, sn, status, is_usable, purpose, purchase_date, category_id, project_id)
values ('DIR-N-108', null, 1, 'RAM: 16G CPU: 8core', 1000, 'LSBX2433', '부팅 느림', 'SAMSUNG', 'ND6179NBK', '노후', true, '개발', '2021-09-01', 1, null);
INSERT INTO devices (id, user_id, manage_dep, spec, price, model, description,  company, sn, status, is_usable, purpose, purchase_date, category_id, project_id)
values ('DIR-N-109', null, 1, 'RAM: 16G CPU: 8core', 1000, 'LSBX2433', '부팅 느림', 'SAMSUNG', 'ND6179NBK', '정상', true, '개발', '2021-09-01', 2, 1);
INSERT INTO devices (id, user_id, manage_dep, spec, price, model, description, company, sn, status, is_usable, purpose, purchase_date, category_id, project_id)
values ('DIR-N-110', null, 1, 'RAM: 16G CPU: 8core', 1000, 'LSBX2433', '부팅 느림', 'SAMSUNG', 'ND6179NBK', '폐기', false, '개발', '2021-09-01', 1, 1);
INSERT INTO devices (id, user_id, manage_dep, spec, price, model, description, company, sn, status, is_usable, purpose, purchase_date, category_id, project_id)
values ('DIR-N-111', null, 1, '품질 최상', 10000, 'LSBX2', '부팅', 'SAMSUNG', 'ND6K', '정상', true, '개발', '2021-09-01', 3, 1);
INSERT INTO devices (id, user_id, manage_dep, spec, price, model, description, company, sn, status, is_usable, purpose, purchase_date, category_id, project_id)
values ('DIR-N-112', null, 1, 'RAM: 2G', 1000, 'LSBXTEST', '폐기함', 'SAMSUNG', 'ND6179NBK', '폐기', false, '개발', '2021-09-01', 1, 1);
INSERT INTO devices (id, user_id, manage_dep, spec, price, model, description, company, sn, status, is_usable, purpose, purchase_date, category_id, project_id)
values ('DIR-N-113', 1, 1, 'RAM: 16G CPU: 8core', 1000, 'LSBX2433', '사용중인 기기', 'SAMSUNG', 'ND6179NBK', '정상', false, '개발', '2021-09-01', 1, 1);
INSERT INTO devices (id, user_id, manage_dep, spec, price, model, description, company, sn, status, is_usable, purpose, purchase_date, category_id, project_id)
values ('DIR-N-114', 1, 1, 'RAM: 16G CPU: 8core', 1000, 'LSBX24', '폐기한 기기', 'SAMSUNG', 'ND9NBK', '폐기', false, '개발', '2021-09-02', 1, 1);

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

INSERT INTO TAGS (name) values ('OS 설치 필요');
INSERT INTO TAGS (name) values ('부팅 느림');

-- INSERT INTO DEVICE_TAG (tag_id, device_id) values (1, 'DIR-N-101');
