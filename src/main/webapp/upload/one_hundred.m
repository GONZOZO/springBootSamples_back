clc
clear all
% 초음파 센서의 위치 설정
sensor1 = [-50, 0];
sensor2 = [50, 0];

% 첫 번째 세트의 추가 좌표 설정
coordinates1 = [
    -50, 0;
    -155, 100;
    -190, 200;
    -220, 300;
    -235, 400;
    -195, 540;
    -100, 590;
    -50,600;
];

% 두 번째 세트의 추가 좌표 설정
coordinates2 = [
    -50, 0;
    45, 100;
    95, 200;
    118, 300;
    125, 400;
    110, 540;
    0, 590;
    -50, 600;
];

% 세 번째 세트의 추가 좌표 설정
coordinates3 = [
    -50, 0;
    -150, 100;
    -190, 200;
    -215, 300;
    -235, 400;
    -200, 540;
    -100, 590;
    -50,600;
];

% 네 번째 세트의 추가 좌표 설정
coordinates4 = [
    -50, 0;
    45, 100;
    96, 200;
    124, 300;
    125, 400;
    110, 540;
    0, 590;
    -50, 600;
];

% 보간을 위한 샘플 데이터 생성
sample_points = 1:0.1:size(coordinates1, 1);

% 각각의 추가 좌표를 보간하여 부드러운 곡선으로 그리기 (파란색)
interp_coordinates1 = interp1(1:size(coordinates1, 1), coordinates1, sample_points, 'pchip');
interp_coordinates2 = interp1(1:size(coordinates2, 1), coordinates2, sample_points, 'pchip');
interp_coordinates3 = interp1(1:size(coordinates3, 1), coordinates3, sample_points, 'pchip');
interp_coordinates4 = interp1(1:size(coordinates4, 1), coordinates4, sample_points, 'pchip');

% 그래프에 점 찍기
scatter([sensor1(1), sensor2(1)], [sensor1(2), sensor2(2)], 100, 'filled', 'Marker', 'o', 'DisplayName', '초음파센서');
hold on;
% scatter(coordinates1(:, 1), coordinates1(:, 2), 100, 'filled', 'Marker', 'x', 'DisplayName', '추가 좌표', 'MarkerEdgeColor', 'red');
% scatter(coordinates2(:, 1), coordinates2(:, 2), 100, 'filled', 'Marker', 'x', 'DisplayName', '추가 좌표', 'MarkerEdgeColor', 'red');
% scatter(coordinates3(:, 1), coordinates3(:, 2), 100, 'filled', 'Marker', 'x', 'DisplayName', '추가 좌표', 'MarkerEdgeColor', 'red');
% scatter(coordinates4(:, 1), coordinates4(:, 2), 100, 'filled', 'Marker', 'x', 'DisplayName', '추가 좌표', 'MarkerEdgeColor', 'red');

% 각각의 추가 좌표를 보간한 부드러운 곡선으로 그리기 (파란색)
% plot(interp_coordinates1(:, 1), interp_coordinates1(:, 2), 'b-', 'LineWidth', 2, 'DisplayName', '부드러운 선1');
% plot(interp_coordinates2(:, 1), interp_coordinates2(:, 2), 'b-', 'LineWidth', 2, 'DisplayName', '부드러운 선2');
% plot(interp_coordinates3(:, 1), interp_coordinates3(:, 2), 'b-', 'LineWidth', 2, 'DisplayName', '부드러운 선3');
% plot(interp_coordinates4(:, 1), interp_coordinates4(:, 2), 'b-', 'LineWidth', 2, 'DisplayName', '부드러운 선4');

plot(interp_coordinates1(:, 1), interp_coordinates1(:, 2),  'Color', [0 0.45 0.74], 'LineWidth', 2, 'DisplayName', '부드러운 선1');
plot(interp_coordinates2(:, 1), interp_coordinates2(:, 2),  'Color', [0 0.45 0.74],'LineWidth', 2, 'DisplayName', '부드러운 선2');
plot(interp_coordinates3(:, 1)+100, interp_coordinates3(:, 2),  'Color', [0 0.45 0.74], 'LineWidth', 2, 'DisplayName', '부드러운 선3');
plot(interp_coordinates4(:, 1)+100, interp_coordinates4(:, 2),  'Color', [0 0.45 0.74], 'LineWidth', 2, 'DisplayName', '부드러운 선4');

% 그래프에 레이블 추가
xlabel('X축 (cm)');
ylabel('Y축 (cm)');
title('초음파센서 감지범위');
%legend('Location', 'Best');
xlim([-300, 300]);
% 그리드 표시
grid on;

hold off;
