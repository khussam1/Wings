<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>Simple Earning App</title>
<style>
  /* Overall styling */
  body {
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    background: linear-gradient(135deg, #6a11cb 0%, #2575fc 100%);
    color: white;
    margin: 0;
    padding: 0;
    height: 100vh;
    display: flex;
    align-items: center;
    justify-content: center;
  }
  /* Container to center the app and constrain width */
  .app-container {
    background: rgba(255, 255, 255, 0.1);
    border-radius: 15px;
    width: 320px;
    max-width: 90vw;
    padding: 25px 20px 30px 20px;
    box-shadow: 0 8px 20px rgba(0, 0, 0, 0.25);
    display: flex;
    flex-direction: column;
    align-items: center;
  }
  /* Title styling */
  h1 {
    margin-bottom: 10px;
    font-weight: 700;
    font-size: 1.8em;
    text-align: center;
  }
  /* Subtitle message */
  p.subtitle {
    font-weight: 400;
    font-size: 0.95em;
    margin-bottom: 25px;
    opacity: 0.8;
    text-align: center;
    line-height: 1.4;
  }
  /* Balance display */
  .balance {
    font-size: 2.5em;
    font-weight: 800;
    margin-bottom: 20px;
    letter-spacing: 2px;
  }
  /* Earn button styling */
  button.earn-btn {
    padding: 14px 0;
    width: 100%;
    border: none;
    border-radius: 30px;
    font-size: 1.2em;
    font-weight: 700;
    background: #ff5e62;
    background: linear-gradient(90deg, #ff5e62 0%, #ff9966 100%);
    color: white;
    box-shadow: 0 4px 15px rgba(255, 94, 98, 0.6);
    cursor: pointer;
    transition: background 0.3s ease;
    user-select: none;
  }
  button.earn-btn:hover {
    background: linear-gradient(90deg, #ff9966 0%, #ff5e62 100%);
  }
  /* Message display after earning */
  .message {
    margin-top: 20px;
    font-size: 1em;
    font-weight: 600;
    color: #d2ffd2;
    height: 24px;
    min-height: 24px;
    text-align: center;
  }
  /* Responsive adjustments for smaller screens */
  @media (max-height: 600px) {
    .app-container {
      height: 90vh;
      overflow-y: auto;
    }
  }
</style>
</head>
<body>
  <div class="app-container" role="main" aria-label="Simple Earning App">
    <h1>Earn Money</h1>
    <p class="subtitle">Tap the button below to earn daily points. Convert points to rewards!</p>
    <div class="balance" aria-live="polite" aria-atomic="true" aria-relevant="text">Points: 0</div>
    <button class="earn-btn" aria-label="Tap to earn 10 points">Earn 10 Points</button>
    <div class="message" aria-live="polite" aria-atomic="true"></div>
  </div>
  <script>
    (function () {
      const earnBtn = document.querySelector('.earn-btn');
      const balanceDisplay = document.querySelector('.balance');
      const messageDisplay = document.querySelector('.message');

      // Initialize points from localStorage or zero
      let points = 0;
      try {
        const savedPoints = localStorage.getItem('earningAppPoints');
        points = savedPoints ? parseInt(savedPoints, 10) : 0;
      } catch (e) {
        points = 0;
      }

      // Update balance UI
      function updateBalance() {
        balanceDisplay.textContent = `Points: ${points}`;
      }

      // Display message for a short time
      function showMessage(text) {
        messageDisplay.textContent = text;
        setTimeout(() => {
          messageDisplay.textContent = '';
        }, 3500);
      }

      // Save points to localStorage
      function savePoints() {
        try {
          localStorage.setItem('earningAppPoints', points.toString());
        } catch (e) {
          // localStorage might be disabled, ignore silently
        }
      }

      // Earn points logic
      function earnPoints() {
        const pointsEarned = 10;
        points += pointsEarned;
        updateBalance();
        savePoints();
        showMessage(`You earned ${pointsEarned} points! Keep going!`);
      }

      // Initialize display on load
      updateBalance();

      // Button click event
      earnBtn.addEventListener('click', () => {
        earnPoints();
      });
    })();
  </script>
</body>
</html>

