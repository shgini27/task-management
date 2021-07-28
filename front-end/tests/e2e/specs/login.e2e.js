// For authoring Nightwatch tests, see
// https://nightwatchjs.org/guide

module.exports = {
  'login page render elements': browser => {
    const loginPage = browser.page.LoginPage()

    loginPage
      .navigate()
      .waitForElementVisible('@app', 500)
      .assert.visible('@usernameInput')
      .assert.visible('@passwordInput')
      .assert.visible('@submitButton')
      .assert.hidden('@formError')

    browser.end()
  },

  'login with invalid credentials': browser => {
    const loginPage = browser.page.LoginPage()

    loginPage
      .navigate()
      .login('not-exist', 'incorrect')

    browser.pause(500)

    loginPage
      .assert.visible('@formError')
      .assert.containsText('@formError', 'Invalid credentials')

    browser
      .assert.urlEquals(browser.launchUrl + 'login')
      .end()
  },

  'login with username': browser => {
    const loginPage = browser.page.LoginPage()
    const homePage = browser.page.HomePage()
    loginPage
      .navigate()
      .login('tigr', 'Tigr@1986')

    browser.pause(2000)

    homePage
      .navigate()
      .expect.element('@pageTitle').text.to.contain('Home Page')

    browser.end()
  },

  'login with email address': browser => {
    const loginPage = browser.page.LoginPage()
    const homePage = browser.page.HomePage()
    loginPage
      .navigate()
      .login('tigr@ttweb.org', 'Tigr@1986')

    browser.pause(2000)

    homePage
      .navigate()
      .expect.element('@pageTitle').text.to.contain('Home Page')

    browser.end()
  }
}
