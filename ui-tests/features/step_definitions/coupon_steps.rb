When(/^I navigate to coupon page using url "([^"]*)"$/) do |url|
  Capybara.page.visit url
end

When(/^I validate coupon\-code "([^"]*)"$/) do |coupon|
  coupon_page.fill_in 'Coupon Code', with: coupon
  coupon_page.validate_button.click
end

And(/^I am on coupon page$/) do
  expect(coupon_page).to have_text 'Apply Coupon'
end