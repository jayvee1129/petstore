-- V2__Seed_pet_data.sql
INSERT INTO pet (name, type, breed, age, price, description, image_url, available, created_at, updated_at) VALUES
('Max', 'Dog', 'Golden Retriever', 3, 500.00, 'Friendly and energetic golden retriever', 'https://via.placeholder.com/300?text=Max', true, NOW(), NOW()),
('Bella', 'Dog', 'Labrador', 2, 550.00, 'Loyal and loving black lab', 'https://via.placeholder.com/300?text=Bella', true, NOW(), NOW()),
('Luna', 'Cat', 'Persian', 1, 300.00, 'Beautiful white Persian cat', 'https://via.placeholder.com/300?text=Luna', true, NOW(), NOW()),
('Rocky', 'Dog', 'German Shepherd', 4, 600.00, 'Strong and intelligent shepherd', 'https://via.placeholder.com/300?text=Rocky', true, NOW(), NOW()),
('Mittens', 'Cat', 'Tabby', 2, 200.00, 'Cute and playful tabby cat', 'https://via.placeholder.com/300?text=Mittens', true, NOW(), NOW()),
('Charlie', 'Dog', 'Beagle', 1, 400.00, 'Small and curious beagle puppy', 'https://via.placeholder.com/300?text=Charlie', true, NOW(), NOW());