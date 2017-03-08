Given(/^I am on login page$/) do
  Capybara.page.visit 'http://localhost:8000'
  expect(login_page.login_form).to have_text 'Login'
end

When(/^I click on login$/) do
  login_page.login_form.submit_button.click_button
end

Then(/^I see the (?:error |)message "([^"]*)"$/) do |expected_text|
  expect(Capybara.page).to have_text expected_text
end

When(/^I login with username "([^"]*)" and password "([^"]*)"$/) do |email, password|
  login_page.login_form.fill_in 'Email', with: email
  login_page.login_form.fill_in 'Password', with: password
  login_page.login_form.submit_button.click_button
end