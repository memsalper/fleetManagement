INSERT INTO vehicles (id, plate) VALUES ('7a75e4aa-9f8d-45e1-8bfe-817337d4578d', '34 TL 34') ON CONFLICT DO NOTHING;
COMMIT;

INSERT INTO delivery_points (id, point_id, point_name) VALUES ('99997feb-438a-496a-932c-e6fc75c1cb0d', 1, 'Branch') ON CONFLICT DO NOTHING;
INSERT INTO delivery_points (id, point_id, point_name) VALUES ('9260ce8d-e521-4031-aefb-05de8fedb234', 2, 'Distribution Center') ON CONFLICT DO NOTHING;
INSERT INTO delivery_points (id, point_id, point_name) VALUES ('40ab2bd8-640b-4279-87f8-e96b37ab2696', 3, 'Transfer Center') ON CONFLICT DO NOTHING;
COMMIT;

INSERT INTO bags (id, bag_barcode, state, delivery_point_id) VALUES ('4c50420a-d564-46d8-88ed-e6908bdc638e', 'C725800', 1, 3) ON CONFLICT DO NOTHING;
INSERT INTO bags (id, bag_barcode, state, delivery_point_id) VALUES ('ff21641a-e466-47a5-9fad-b1272f3df126', 'C725799', 1, 2) ON CONFLICT DO NOTHING;
COMMIT;

INSERT INTO packages (id, package_barcode, package_status, volumetric_weight, delivery_point_id) VALUES ('d44fb959-5bdc-4be9-9e0b-3b740723ef43', 'P8988000120', 1, 33.00, 2) ON CONFLICT DO NOTHING;
INSERT INTO packages (id, package_barcode, package_status, volumetric_weight, delivery_point_id) VALUES ('de95325d-a275-4ff5-a6a8-38838b34a99c', 'P9988000128', 1, 55.00, 3) ON CONFLICT DO NOTHING;
INSERT INTO packages (id, package_barcode, package_status, volumetric_weight, delivery_point_id) VALUES ('d40cbda7-d472-4acb-9289-9a42b4dde33d', 'P9988000129', 1, 28.00, 3) ON CONFLICT DO NOTHING;
INSERT INTO packages (id, package_barcode, package_status, volumetric_weight, delivery_point_id) VALUES ('9df80bae-f0a4-4aec-9655-d6524975910f', 'P8988000121', 1, 17.00, 2) ON CONFLICT DO NOTHING;
INSERT INTO packages (id, package_barcode, package_status, volumetric_weight, delivery_point_id) VALUES ('b0a36c90-340e-4e06-81e6-ff4f528a0d37', 'P8988000122', 1, 26.00, 2) ON CONFLICT DO NOTHING;
INSERT INTO packages (id, package_barcode, package_status, volumetric_weight, delivery_point_id) VALUES ('d22e54e8-aef9-4161-bfa7-cbe707b7a59c', 'P8988000126', 1, 50.00, 2) ON CONFLICT DO NOTHING;
INSERT INTO packages (id, package_barcode, package_status, volumetric_weight, delivery_point_id) VALUES ('17f2cfa8-34c4-4d99-b13e-9377e971387a', 'P9988000126', 1, 15.00, 3) ON CONFLICT DO NOTHING;
INSERT INTO packages (id, package_barcode, package_status, volumetric_weight, delivery_point_id) VALUES ('501d0d0c-df23-4c25-bae5-91af006ca6de', 'P9988000127', 1, 16.00, 3) ON CONFLICT DO NOTHING;
INSERT INTO packages (id, package_barcode, package_status, volumetric_weight, delivery_point_id) VALUES ('f7c1fc6b-a199-4561-a694-978c9fef1238', 'P9988000130', 1, 17.00, 3) ON CONFLICT DO NOTHING;
INSERT INTO packages (id, package_barcode, package_status, volumetric_weight, delivery_point_id) VALUES ('33d8a132-bded-435e-abe9-7aa6b88f13cd', 'P7988000121', 1, 5.00, 1) ON CONFLICT DO NOTHING;
INSERT INTO packages (id, package_barcode, package_status, volumetric_weight, delivery_point_id) VALUES ('3f6e4927-6d12-4106-8404-a24462886ab5', 'P7988000122', 1, 5.00, 1) ON CONFLICT DO NOTHING;
INSERT INTO packages (id, package_barcode, package_status, volumetric_weight, delivery_point_id) VALUES ('a44ba167-28a8-4213-9b68-b6d055cbe060', 'P7988000123', 1, 9.00, 1) ON CONFLICT DO NOTHING;
INSERT INTO packages (id, package_barcode, package_status, volumetric_weight, delivery_point_id) VALUES ('c4187363-0054-405a-b937-ab41ad315098', 'P8988000123', 1, 35.00, 2) ON CONFLICT DO NOTHING;
INSERT INTO packages (id, package_barcode, package_status, volumetric_weight, delivery_point_id) VALUES ('b6bd5eb7-fa5d-4857-b952-0172a16c2b52', 'P8988000124', 1, 1.00, 2) ON CONFLICT DO NOTHING;
INSERT INTO packages (id, package_barcode, package_status, volumetric_weight, delivery_point_id) VALUES ('ae241e73-f238-4f49-b65c-c5a645168b1b', 'P8988000125', 1, 200.00, 2) ON CONFLICT DO NOTHING;
COMMIT;


INSERT INTO packages_to_bag (bag_barcode, package_barcode, id) VALUES ('C725799', 'P8988000122', '81c9b707-43fb-4f8c-acc8-aef5ce9f7e12') ON CONFLICT DO NOTHING;
INSERT INTO packages_to_bag (bag_barcode, package_barcode, id) VALUES ('C725799', 'P8988000126', 'ccffbfde-5bff-4f86-85dd-d91f36cb8c68') ON CONFLICT DO NOTHING;
INSERT INTO packages_to_bag (bag_barcode, package_barcode, id) VALUES ('C725800', 'P9988000128', 'f4172270-9aaf-4c84-a424-6ff324826c7e') ON CONFLICT DO NOTHING;
INSERT INTO packages_to_bag (bag_barcode, package_barcode, id) VALUES ('C725800', 'P9988000129', 'e8d9ba70-81b7-4eef-b4a0-1de7deb74622') ON CONFLICT DO NOTHING;
COMMIT;