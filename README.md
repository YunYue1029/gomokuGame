# Gomoku 線上五子棋對戰遊戲

這是一個以 Java Socket 通訊 + Swing 圖形介面製作的雙人連線五子棋遊戲，分為伺服器（Server）與用戶端（Client）兩部分。支援兩位玩家透過同一區網或本機連線對戰。

---

## 功能特色

- 支援兩位玩家連線對戰
- 使用 Swing 實作圖形化棋盤（15x15）
- 玩家下棋即時同步給對方
- 基本勝負判斷（五子連線即勝）
- 結束時彈出視窗提示勝者

---

## 遊戲操作說明
- 第一位連線玩家使用「O」，第二位使用「X」
- 玩家輪流點擊空格進行落子
- 先連成五子者獲勝，顯示提示視窗後關閉遊戲
